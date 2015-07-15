/*
 * Hibernate OGM, Domain model persistence for NoSQL datastores
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.bugs;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OptionalStoryBranch {

	// Analyze.NO for filtering in query
	// Store.YES for projection in query
	private String evilText;

	private String goodText;

	@AttributeOverrides({
		@AttributeOverride(name = "score", column = @Column(name = "evil_ending_score")),
		@AttributeOverride(name = "text", column = @Column(name = "evil_ending_text"))
	})
	private Ending evilEnding;

	@AttributeOverrides({
		@AttributeOverride(name = "score", column = @Column(name = "good_ending_score")),
		@AttributeOverride(name = "text", column = @Column(name = "good_ending_text"))
	})
	private Ending goodEnding;

	public OptionalStoryBranch() {
	}

	public OptionalStoryBranch(String evilText, String goodText, Ending evilEnding) {
		this.evilText = evilText;
		this.goodText = goodText;
		this.evilEnding = evilEnding;
	}

	public String getEvilText() {
		return evilText;
	}

	public void setEvilText(String embeddedString) {
		this.evilText = embeddedString;
	}

	public Ending getEvilEnding() {
		return evilEnding;
	}

	public void setEvilEnding(Ending anotherEmbeddable) {
		this.evilEnding = anotherEmbeddable;
	}

	public String getGoodText() {
		return goodText;
	}

	public void setGoodText(String anotherItem) {
		this.goodText = anotherItem;
	}

	public Ending getGoodEnding() {
		return goodEnding;
	}

	public void setGoodEnding(Ending goodEnding) {
		this.goodEnding = goodEnding;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( evilEnding == null ) ? 0 : evilEnding.hashCode() );
		result = prime * result + ( ( evilText == null ) ? 0 : evilText.hashCode() );
		result = prime * result + ( ( goodEnding == null ) ? 0 : goodEnding.hashCode() );
		result = prime * result + ( ( goodText == null ) ? 0 : goodText.hashCode() );
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( getClass() != obj.getClass() ) {
			return false;
		}
		OptionalStoryBranch other = (OptionalStoryBranch) obj;
		if ( evilEnding == null ) {
			if ( other.evilEnding != null ) {
				return false;
			}
		}
		else if ( !evilEnding.equals( other.evilEnding ) ) {
			return false;
		}
		if ( evilText == null ) {
			if ( other.evilText != null ) {
				return false;
			}
		}
		else if ( !evilText.equals( other.evilText ) ) {
			return false;
		}
		if ( goodEnding == null ) {
			if ( other.goodEnding != null ) {
				return false;
			}
		}
		else if ( !goodEnding.equals( other.goodEnding ) ) {
			return false;
		}
		if ( goodText == null ) {
			if ( other.goodText != null ) {
				return false;
			}
		}
		else if ( !goodText.equals( other.goodText ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "OptionalStoryBranch [evilText=" );
		builder.append( evilText );
		builder.append( ", goodText=" );
		builder.append( goodText );
		builder.append( ", evilEnding=" );
		builder.append( evilEnding );
		builder.append( ", goodEnding=" );
		builder.append( goodEnding );
		builder.append( "]" );
		return builder.toString();
	}
}
