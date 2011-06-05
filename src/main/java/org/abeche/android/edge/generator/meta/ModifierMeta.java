/**
 * 
 */
package org.abeche.android.edge.generator.meta;

import java.util.EnumSet;
import java.util.Set;

import javax.lang.model.element.Modifier;


/**
 * @author abeche
 *
 */
public enum ModifierMeta {
	NONE("")
	,PUBLIC("public"),PROTECTED("protected"),PRIVATE("private")
	,ABSTRACT("abstract"),FINAL("final"),NATIVE("native"),STATIC("static")
	,STRICTFP("strictfp"),SYNCHRONIZED("synchronized"),TRANSIENT("transient")
	,VOLATILE("volatile");
	
	private String name = "";
	
	private ModifierMeta(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isNull() {
		return (name != null) || (name.isEmpty());
	}
	
	@Override
	public String toString() {
		return name;
	}

	public static EnumSet<ModifierMeta> toMeta(Set<Modifier> modifiers) {
		EnumSet<ModifierMeta> result = EnumSet.noneOf(NONE.getDeclaringClass());
		for (Modifier modifier : modifiers) {
			for(ModifierMeta meta : ModifierMeta.values()) {
				if (meta.getName().equals(modifier.name())) {
					result.add(meta);
				}
			}
		}
		return result;
	}
}
