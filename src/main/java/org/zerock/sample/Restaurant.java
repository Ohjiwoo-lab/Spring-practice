package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component
@Data
public class Restaurant {
	 // ������ �ÿ� �ڵ����� setChef() �޼ҵ带 ����. onMethod_ �� ������ setChef() �޼ҵ忡 @Autowired  ������̼��� �߰�
 @Setter(onMethod_ = @Autowired) 
 private Chef chef;
 
 //Chef chef = new Chef();

}

