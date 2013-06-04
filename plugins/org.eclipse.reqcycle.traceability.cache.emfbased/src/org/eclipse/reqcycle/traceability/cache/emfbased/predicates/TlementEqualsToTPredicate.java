package org.eclipse.reqcycle.traceability.cache.emfbased.predicates;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;

public class TlementEqualsToTPredicate <S,T> implements Predicate<T> {

	private S s;

	public TlementEqualsToTPredicate  (S  s){
		this.s = s;
	}
	
	@Override
	public boolean apply(T arg0) {
		return Objects.equal(arg0, s);
	}

}
