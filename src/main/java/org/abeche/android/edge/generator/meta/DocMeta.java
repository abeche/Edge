/**
 * 
 */
package org.abeche.android.edge.generator.meta;


/**
 * @author abeche
 *
 */
public class DocMeta {
	private String comment = "";

	public DocMeta() {
	}
	
	public DocMeta(String comment) {
		this.comment = comment;
	}
	
	public static DocMeta doc(String comment) {
		return new DocMeta(comment);
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public boolean isEmpty() {
		return (comment == null) || (comment.isEmpty());
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result
			.append("/**%n")
			.append(" * ").append(comment).append("%n")
			.append(" */%n");
		return result.toString();
	}
}
