package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component
@ToString
@Getter
@RequiredArgsConstructor
public class Hotel {

	@NonNull
	private Chef chef;

	private String name;
	
	//public Hotel(Chef chef) {
	//	this.chef = chef;
	//}
}
