package com.test.proj.rickandmorty.repository;

import com.test.proj.rickandmorty.entity.Episode;
import org.springframework.data.repository.CrudRepository;

public interface EpisodeRepository extends CrudRepository<Episode, Long> {
}
