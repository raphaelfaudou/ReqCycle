package org.eclipse.reqcycle.uri.visitors;

public interface IVisitable {
	void accept(IVisitor visitor);

	void dispose();
}
