/*******************************************************************************
 *  * Copyright (c) 2013-2014 AtoS.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * which accompanies this distribution, and is available at
 *  * http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  * Contributors:
 *  *    {INITIAL AUTHOR} - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.ui.services.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.util.OpenStrategy;
import org.polarsys.reqcycle.traceability.ui.services.ILocateService;
import org.polarsys.reqcycle.uri.IReachableManager;
import org.polarsys.reqcycle.uri.exceptions.IReachableHandlerException;
import org.polarsys.reqcycle.uri.model.IReachableHandler;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.uri.model.ReachableObject;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.ide.ResourceUtil;

@Singleton
public class LocateService implements ILocateService {

	@Inject
	IReachableManager reachableManager;

	@Override
	public boolean isOpenable(Reachable reachable) {
		if (reachable == null) {
			return false;
		}
		try {
			String protocol = reachable.getScheme();
			if ("http".equalsIgnoreCase(protocol)
					|| "https".equalsIgnoreCase(protocol)) {
				return true;
			}
			IReachableHandler handler = reachableManager
					.getHandlerFromReachable(reachable);
			ReachableObject fromReachable = handler.getFromReachable(reachable);
			IMarker marker = getMarker(fromReachable);
			if (marker != null) {
				return true;
			}
			IFile file = getFile(fromReachable);
			if (file != null) {
				return true;
			}
			File f = getFileExternal(fromReachable);
			if (f != null) {
				return true;
			}

		} catch (IReachableHandlerException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void open(Reachable reachable) throws Exception {
		if (reachable == null) {
			return;
		}
		try {
			String protocol = reachable.getScheme();
			if ("http".equalsIgnoreCase(protocol)
					|| "https".equalsIgnoreCase(protocol)) {
				openBrowser(reachable);
			}
			IReachableHandler handler = reachableManager
					.getHandlerFromReachable(reachable);
			ReachableObject fromReachable = handler.getFromReachable(reachable);
			IMarker marker = getMarker(fromReachable);
			if (marker != null) {
				openIFileEditor(marker);
				marker.delete();
			}
			IFile file = getFile(fromReachable);
			if (file != null) {
				openIFileEditor(file);
			}
			File f = getFileExternal(fromReachable);
			if (f != null) {
				openFileEditor(f);
			}
		} catch (IReachableHandlerException e) {
			e.printStackTrace();
		}

	}

	protected void openFileEditor(File f) throws Exception {
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		IFileStore fileStore = EFS.getLocalFileSystem().getStore(
				new Path(f.getAbsolutePath()));
		IDE.openEditorOnFileStore(page, fileStore);
	}

	protected void openIFileEditor(IFile file) throws Exception {
		IMarker marker = file.createMarker("LOCATION");
		openIFileEditor(marker);
	}

	protected void openIFileEditor(IMarker marker) throws PartInitException,
			CoreException {
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		IEditorPart editor = null;
		for (IEditorReference ref : page.getEditorReferences()) {
			IEditorInput input = ref.getEditorInput();
			IFile fileFromEditor = ResourceUtil.getFile(input);
			if (fileFromEditor != null) {
				if (marker.getResource().equals(fileFromEditor)
						&& OpenStrategy.activateOnOpen()) {
					editor = ref.getEditor(true);
					page.activate(editor);

				}
			}
		}
		// the same as the first part of the code, but the first is optimized
		try {
			editor = IDE
					.openEditor(page, marker, OpenStrategy.activateOnOpen());
		} catch (PartInitException e) {
		}
		if (editor instanceof IGotoMarker) {
			((IGotoMarker) editor).gotoMarker(marker);
		}
	}

	protected void openBrowser(Reachable reachable) throws Exception {
		IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench()
				.getBrowserSupport();
		IWebBrowser browser;
		try {
			browser = browserSupport.createBrowser(
					IWorkbenchBrowserSupport.LOCATION_BAR
							| IWorkbenchBrowserSupport.NAVIGATION_BAR,
					browserSupport.getExternalBrowser().getId(), "Browser", "");
			URI uri = reachable.getURI();
			browser.openURL(uri.toURL());
		} catch (PartInitException e) {
		} catch (MalformedURLException e) {
			throw new Exception("impossible to open in browser "
					+ reachable.toString());
		}
	}

	protected IFile getFile(ReachableObject reachable) {
		IFile res = (IFile) reachable.getAdapter(IFile.class);
		if (res == null) {
			res = (IFile) Platform.getAdapterManager().getAdapter(reachable,
					IFile.class);
		}
		return res;
	}

	protected File getFileExternal(ReachableObject reachable) {
		File res = (File) reachable.getAdapter(File.class);
		if (res == null) {
			res = (File) Platform.getAdapterManager().getAdapter(reachable,
					File.class);
		}
		return res;
	}

	protected IMarker getMarker(ReachableObject reachable) {
		IMarker res = (IMarker) reachable.getAdapter(IMarker.class);
		if (res == null) {
			res = (IMarker) Platform.getAdapterManager().getAdapter(reachable,
					IMarker.class);
		}
		return res;
	}

}
