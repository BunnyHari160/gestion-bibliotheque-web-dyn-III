package com.maBibliotheque.service;

import com.maBibliotheque.repository.AdherentRepository;
import java.util.List;
import java.util.Map;

public class AdherentService {
    private final AdherentRepository adherentRepository;

    public AdherentService(AdherentRepository adherentRepository) {
        this.adherentRepository = adherentRepository;
    }

    public List<Map<String, Object>> getAdherentsActifs() {
        return adherentRepository.findAllAdherents();
    }
}
