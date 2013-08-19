package org.eclipse.reqcycle.traceability.types.ui.impl;

import javax.inject.Inject;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.types.ITypesConfigurationProvider;
import org.eclipse.reqcycle.traceability.types.RelationBasedType;
import org.eclipse.reqcycle.traceability.types.RelationUtils;
import org.eclipse.reqcycle.traceability.types.configuration.predicates.ReqCycleDynamicPackage;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.DecorationPredicate;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.RelationsPredicatesMapping;
import org.eclipse.reqcycle.traceability.types.ui.IStylePredicateProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

public class StylePredicateProvider implements IStylePredicateProvider {
	@Inject
	ITypesConfigurationProvider provider;

	public <T> T getResourcetForRelation(Link link, IBehavior<T> behav) {
		Configuration defaultConfiguration = provider.getDefaultConfiguration();
		if (defaultConfiguration == null) {
			return null;
		}
		if (!(link.getKind() instanceof RelationBasedType)) {
			return null;
		}
		Relation r = RelationUtils.getRelation(link.getKind().getLabel(),
				defaultConfiguration);
		if (r == null) {
			return null;
		}
		RelationsPredicatesMapping mapping = getMapping(r, defaultConfiguration);
		if (mapping == null) {
			return null;
		}
		EObject eobject = ReqCycleDynamicPackage.getEObject(link);
		for (DecorationPredicate p : mapping.getDecorations()) {
			if (p.getPredicate().match(eobject)) {
				return behav.handleRegistry(p);
			}
		}
		return null;
	}

	private static interface IBehavior<T> {
		T handleRegistry(DecorationPredicate t);
	}

	private RelationsPredicatesMapping getMapping(Relation rel,
			Configuration conf) {
		for (RelationsPredicatesMapping m : conf.getParent().getMappings()) {
			if (m.getRelation() != null && m.getRelation().equals(rel)) {
				return m;
			}
		}
		return null;
	}

	public Color getColorForRelation(Link link) {
		return getResourcetForRelation(link, new IBehavior<Color>() {

			@Override
			public Color handleRegistry(DecorationPredicate t) {
				Color result = JFaceResources.getColorRegistry().get(
						t.getColor());
				if (result == null) {
					JFaceResources.getColorRegistry().put(t.getColor(),
							StringConverter.asRGB(t.getColor()));
					result = JFaceResources.getColorRegistry()
							.get(t.getColor());
				}
				return result;
			}
		});
	}

	public Font getFontForRelation(Link link) {
		return getResourcetForRelation(link, new IBehavior<Font>() {

			@Override
			public Font handleRegistry(DecorationPredicate t) {
				Font result = JFaceResources.getFontRegistry()
						.get(t.getStyle());
				if (result == null) {
					JFaceResources.getFontRegistry().put(t.getStyle(),
							StringConverter.asFontDataArray(t.getStyle()));
					result = JFaceResources.getFontRegistry().get(t.getStyle());
				}
				return result;
			}
		});
	}
}
