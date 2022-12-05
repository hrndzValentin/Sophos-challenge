package com.example.demo.services.implementation;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	public Affiliates sendAffiliate(Affiliates affiliates) {
		try {
		Affiliates newAffiliate = affiliatesRepository.save(affiliates);
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
	public Affiliates updateAffiliate(Affiliates affiliates) {
		Optional<Affiliates> optionalAffiliate = affiliatesRepository.findById(affiliates.getAffiliateId());

		if (optionalAffiliate.isPresent()) {
			try {
			Affiliates updateAffiliate = optionalAffiliate.get();
			updateAffiliate.setName(affiliates.getName());
			updateAffiliate.setAge(affiliates.getAge());
			updateAffiliate.setMail(affiliates.getMail());
			affiliatesRepository.save(updateAffiliate);
			return updateAffiliate;
			}catch(Exception e) {
				return null;
			}
		}
			
		return null;
		
	}

}
