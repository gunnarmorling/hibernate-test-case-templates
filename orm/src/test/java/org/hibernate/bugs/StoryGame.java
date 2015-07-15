/*
 * Hibernate OGM, Domain model persistence for NoSQL datastores
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.bugs;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Simulate a multi-path story game.
 * It can be used to test embeddable elements and collections.
 *
 * @author Davide D'Alto
 */
@Entity
public class StoryGame {

	@Id
	private Long id;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "storyText", column = @Column(name = "good_story_text")),
			@AttributeOverride(name = "additionalEndings", column = @Column(name = "good_additional_endings")),
			@AttributeOverride(name = "ending.score", column = @Column(name = "good_ending_score")),
			@AttributeOverride(name = "ending.text", column = @Column(name = "good_ending_text"))

	})
	private StoryBranch goodBranch;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "storyText", column = @Column(name = "evil_story_text")),
			@AttributeOverride(name = "additionalEndings", column = @Column(name = "evil_additional_endings")),
			@AttributeOverride(name = "ending.score", column = @Column(name = "evil_ending_score")),
			@AttributeOverride(name = "ending.text", column = @Column(name = "evil_ending_text"))
	})
	private StoryBranch evilBranch;

	@ElementCollection
	private List<OptionalStoryBranch> chaoticBranches;

	@ElementCollection
	private List<OptionalStoryBranch> neutralBranches;

	public StoryGame() {
	}

	public StoryGame(Long id, StoryBranch goodBranch) {
		this.id = id;
		this.goodBranch = goodBranch;
	}

	public Long getId() {
		return id;
	}

	public void setGoodBranch(StoryBranch goodBranch) {
		this.goodBranch = goodBranch;
	}

	public StoryBranch getGoodBranch() {
		return goodBranch;
	}

	public StoryBranch getEvilBranch() {
		return evilBranch;
	}

	public void setEvilBranch(StoryBranch evilBranch) {
		this.evilBranch = evilBranch;
	}

	public List<OptionalStoryBranch> getChaoticBranches() {
		return chaoticBranches;
	}

	public void setChaoticBranches(List<OptionalStoryBranch> chaoticBranchhes) {
		this.chaoticBranches = chaoticBranchhes;
	}

	public List<OptionalStoryBranch> getNeutralBranches() {
		return neutralBranches;
	}

	public void setNeutralBranches(List<OptionalStoryBranch> neutralBranches) {
		this.neutralBranches = neutralBranches;
	}
}
