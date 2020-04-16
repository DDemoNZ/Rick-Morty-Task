package com.test.proj.rickandmorty.service.impl;

import com.test.proj.rickandmorty.entity.Episode;
import com.test.proj.rickandmorty.repository.EpisodeRepository;
import com.test.proj.rickandmorty.service.EpisodeService;
import org.springframework.stereotype.Service;

@Service
public class EpisodeServiceImpl implements EpisodeService {

    private final EpisodeRepository episodeRepository;

    public EpisodeServiceImpl(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    @Override
    public void saveOrUpdate(Episode episode) {
        episodeRepository.save(episode);
    }
}
