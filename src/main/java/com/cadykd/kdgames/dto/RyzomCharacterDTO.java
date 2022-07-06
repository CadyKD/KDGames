package com.cadykd.kdgames.dto;

import com.cadykd.kdgames.models.RyzomCharacter;
import com.cadykd.kdgames.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RyzomCharacterDTO {
	@NotNull
	@NotEmpty(message="{NotEmpty.Character.Missing}")
	private String characterName;
	
	@NotNull
	private RyzomCharacter.CharacterRace race;
	
	@NotNull
	private RyzomCharacter.CharacterGender gender;
}
