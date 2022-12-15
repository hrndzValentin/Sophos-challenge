package com.example.demo.services;

import java.util.List;

import com.example.demo.DTOS.AffiliatesDTO;
import com.example.demo.Entitys.Affiliates;

public interface AffiliatesService {

	List<Affiliates> searchAffiliates();
	Affiliates searchAffiliateById(int id);
	Affiliates sendAffiliate(AffiliatesDTO affiliatesDTO);
	Void removeAffiliate(int id);
	Affiliates updateAffiliate(AffiliatesDTO affiliatesDTO, int id);
}
