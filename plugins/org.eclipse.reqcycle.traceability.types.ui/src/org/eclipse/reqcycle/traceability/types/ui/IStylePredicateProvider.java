package org.eclipse.reqcycle.traceability.types.ui;

import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

public interface IStylePredicateProvider {
	Font getFontForRelation(Link link);

	Color getColorForRelation(Link link);
}
