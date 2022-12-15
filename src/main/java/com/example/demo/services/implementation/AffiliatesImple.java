package com.example.demo.services.implementation;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTOS.AffiliatesDTO;
import com.example.demo.Entitys.Affiliates;
import com.example.demo.Repositories.AffiliatesRepository;
import com.example.demo.services.AffiliatesService;

@Service
public class AffiliatesImple implements AffiliatesService {

	@Autowired
	private AffiliatesRepository affiliatesRepository;

	@Override
	public List<Affiliates> searchAffiliates() {
		List<Affiliates> affiliates = affiliatesRepository.findAll();
		if(affiliates.isEmpty()) {
			return null;
		}
		return affiliates;
	}

	@Override
	public Affiliates searchAffiliateById(int id) {
		Optional<Affiliates> optionalAffiliate = affiliatesRepository.findById(id);

		if (optionalAffiliate.isPresent()) {
			return optionalAffiliate.get();
		} else {
			return null;
		}
	}

	@Override
	public Affiliates sendAffiliate(AffiliatesDTO affiliatesDTO) {
		try {
		Affiliates affiliate = new Affiliates();
		affiliate.setName(affiliatesDTO.getName());
		affiliate.setAge(affiliatesDTO.getAge());
		affiliate.setMail(affiliatesDTO.getMail());
		
		Affiliates newAffiliate = affiliatesRepository.save(affiliate);
		return newAffiliate;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Void removeAffiliate(int id) {
		affiliatesRepository.deleteById(id);
		return null;
		
	}

	@Override
	public Affiliates updateAffiliate(AffiliatesDTO affiliatesDTO, int id) {
		Optional<Affiliates> optionalAffiliate = affiliatesRepository.findById(id);
		if (optionalAffiliate.isPresent()) {
			optionalAffiliate.get().setName(affiliatesDTO.getName());
			optionalAffiliate.get().setAge(affiliatesDTO.getAge());
			optionalAffiliate.get().setMail(affiliatesDTO.getMail());
			affiliatesRepository.save(optionalAffiliate.get());
			return optionalAffiliate.get();
			
		}
			
		return null;
		
	}

}
