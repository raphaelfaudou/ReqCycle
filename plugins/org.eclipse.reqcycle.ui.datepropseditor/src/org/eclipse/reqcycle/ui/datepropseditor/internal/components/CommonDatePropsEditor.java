/*****************************************************************************
 * Copyright (c) 2013 AtoS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Papa Issa DIAKHATE (AtoS) papa-issa.diakhate@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.reqcycle.ui.datepropseditor.internal.components;

import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.TimeZone;
import java.util.TreeSet;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;

class CommonDatePropsEditor extends Composite {

	/** The value of the Calendar (date + time + timezone) set. */
	private final Calendar calendarValue;

	private DateTime dateCalendar;

	private DateTime dateTime;

	private ComboViewer comboViewer;

	CommonDatePropsEditor(EAttribute attribute, Composite parent, int style) {

		super(parent, style);
		setLayout(new GridLayout(3, false));

		this.calendarValue = Calendar.getInstance();

		Label lblName = new Label(this, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblName.setText(attribute.getName());

		final Composite calendarAndTimeComposite = new Composite(this, SWT.NONE);
		calendarAndTimeComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		calendarAndTimeComposite.setLayout(new FillLayout(SWT.HORIZONTAL));

		this.initDateCalendar(calendarAndTimeComposite);

		this.initDateTime(calendarAndTimeComposite);

		this.initComboTimezones(parent);
	}

	Calendar getCalendarValue() {
		return this.calendarValue;
	}

	private void initDateCalendar(Composite parent) {
		dateCalendar = new DateTime(parent, SWT.BORDER | SWT.DROP_DOWN);
		dateCalendar.setToolTipText("Calendar");
		dateCalendar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				calendarValue.set(Calendar.YEAR, dateCalendar.getYear());
				calendarValue.set(Calendar.MONTH, dateCalendar.getMonth());
				calendarValue.set(Calendar.DAY_OF_MONTH, dateCalendar.getDay());
			}
		});
	}

	private void initDateTime(Composite parent) {
		dateTime = new DateTime(parent, SWT.BORDER | SWT.TIME);
		dateTime.setToolTipText("Time");
		dateTime.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				calendarValue.set(Calendar.HOUR_OF_DAY, dateTime.getHours());
				calendarValue.set(Calendar.MINUTE, dateTime.getMinutes());
				calendarValue.set(Calendar.SECOND, dateTime.getSeconds());
			}
		});
	}

	private void initComboTimezones(Composite parent) {
		comboViewer = new ComboViewer(this, SWT.READ_ONLY);
		Combo comboTimezones = comboViewer.getCombo();
		comboTimezones.setToolTipText("Timezone");
		comboTimezones.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.setLabelProvider(new LabelProvider() {

			@Override
			public String getText(Object element) {
				if(element instanceof TimeZone) {
					return ((TimeZone)element).getID();
				}
				return super.getText(element);
			}
		});
		comboViewer.setInput(getAvailableTimezones());
		comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if(comboViewer.getSelection() instanceof IStructuredSelection) {
					final IStructuredSelection selection = (IStructuredSelection)comboViewer.getSelection();
					if(!(selection.isEmpty())) {
						final TimeZone seletedTimezone = (TimeZone)selection.getFirstElement();
						calendarValue.setTimeZone(seletedTimezone);
					}
				}
			}
		});
	}

	private static TimeZone[] getAvailableTimezones() {
		final Collection<TimeZone> availableTimezones = new TreeSet<TimeZone>(new TimeZoneComparator());
		for(final String id : TimeZone.getAvailableIDs()) {
			availableTimezones.add(TimeZone.getTimeZone(id));
		}
		return availableTimezones.toArray(new TimeZone[availableTimezones.size()]);
	}

	private static class TimeZoneComparator implements Comparator<TimeZone> {

		@Override
		public int compare(TimeZone tz1, TimeZone tz2) {
			return tz1.getID().compareTo(tz2.getID());
		}
	}

}
