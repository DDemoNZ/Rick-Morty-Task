package com.test.proj.rickandmorty.util;

import com.test.proj.rickandmorty.entity.Characters;
import java.util.List;

public interface JsonParseToDb {
    List<Characters> parseJson(String json);
}
