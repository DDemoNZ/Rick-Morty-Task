package com.test.proj.rickandmorty.util;

import com.test.proj.rickandmorty.entity.Characters;
import com.test.proj.rickandmorty.entity.Episode;
import com.test.proj.rickandmorty.entity.Location;
import com.test.proj.rickandmorty.service.CharactersService;
import com.test.proj.rickandmorty.service.EpisodeService;
import com.test.proj.rickandmorty.service.LocationService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JsonParseToDbImpl implements JsonParseToDb {

    private final CharactersService charactersService;
    private final EpisodeService episodeService;
    private final LocationService locationService;
    private final HttpConnection httpConnection;

    @Value("${rickandmorty.url}")
    private String url;

    public JsonParseToDbImpl(CharactersService charactersService, EpisodeService episodeService,
                             LocationService locationService, HttpConnection httpConnection) {
        this.charactersService = charactersService;
        this.episodeService = episodeService;
        this.locationService = locationService;
        this.httpConnection = httpConnection;
    }

    @Override
    public List<Characters> parseJson(String json) {

        JSONObject jsonObject = new JSONObject(json);
        JSONArray resultsArray = jsonObject.getJSONArray("results");
        List<Characters> charactersList = new ArrayList<>();

        for (int i = 0; i < resultsArray.length(); i++) {
            Characters character = new Characters();
            character.setId(resultsArray.getJSONObject(i).getLong("id"));
            character.setName(resultsArray.getJSONObject(i).getString("name"));
            character.setStatus(resultsArray.getJSONObject(i).getString("status"));
            character.setSpecies(resultsArray.getJSONObject(i).getString("species"));
            character.setType(resultsArray.getJSONObject(i).getString("type"));
            character.setGender(resultsArray.getJSONObject(i).getString("gender"));
            character.setImage(resultsArray.getJSONObject(i).getString("image"));
            character.setUrl(resultsArray.getJSONObject(i).getString("url"));
            character.setCreated(resultsArray.getJSONObject(i).getString("created"));
            character.setLocation(getLocation(i, resultsArray));
            character.setOrigin(getOrigin(i, resultsArray));
            character.setEpisode(getEpisode(i, resultsArray));

            charactersList.add(character);
        }
        return charactersList;
    }

    private Location getLocation(int i, JSONArray resultsArray) {
        Location location = new Location();
        location.setName(resultsArray.getJSONObject(i).getJSONObject("location").getString("name"));
        location.setUrl(resultsArray.getJSONObject(i).getJSONObject("location").getString("url"));
        return location;
    }

    private Location getOrigin(int i, JSONArray resultsArray) {
        Location origin = new Location();
        origin.setName(resultsArray.getJSONObject(i).getJSONObject("origin").getString("name"));
        origin.setUrl(resultsArray.getJSONObject(i).getJSONObject("origin").getString("url"));
        return origin;
    }

    private Set<Episode> getEpisode(int i, JSONArray resultsArray) {
        Set<Episode> episodeSet = new HashSet<>();
        JSONArray episodesJsonArray = resultsArray.getJSONObject(i).getJSONArray("episode");
        for (int j = 0; j < episodesJsonArray.length(); j++) {
            Episode episode = new Episode();
            episode.setUrl(episodesJsonArray.getJSONObject(i).getString("url"));
            episodeSet.add(episode);
        }
        return episodeSet;
    }
}
