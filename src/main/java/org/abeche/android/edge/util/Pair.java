/**
 * 
 */
package org.abeche.android.edge.util;

/**
 * @author abeche
 *
 */
public class Pair<V1, V2> {
	private V1 v1;
	private V2 v2;
	
	public Pair(V1 v1, V2 v2) {
		this.v1 = v1;
		this.v2 = v2;
	}

	public V1 get1() {
		return v1;
	}

	public void set1(V1 v1) {
		this.v1 = v1;
	}

	public V2 get2() {
		return v2;
	}

	public void set2(V2 v2) {
		this.v2 = v2;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName() + "[v1="+v1+", v2="+v2+"]";
	}
	
	private boolean objEquals(Object left, Object right) {
		boolean result = false;
		if (left == null) {
			if (right == null) {
				result = true;
			} else {
				result = false;
			}
		} else {
			result = right.equals(left);
		}
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o instanceof Pair) {
			Pair<?, ?> other = (Pair<?, ?>) o;
			return objEquals(this.v1, other.v1)
				&& objEquals(this.v2, other.v2);
		} else {
			return false;
		}
	}
	
	public static <V1, V2> Pair<V1, V2> of(V1 left, V2 right) {
		return new Pair<V1, V2>(left, right);
		
	}
}
