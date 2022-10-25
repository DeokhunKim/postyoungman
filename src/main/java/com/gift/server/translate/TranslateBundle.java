package com.gift.server.translate;

import com.gift.server.gift.Gift;
import lombok.Data;
import lombok.experimental.Delegate;

import java.util.ArrayList;
import java.util.List;

@Data
public class TranslateBundle {
    @Delegate
    private List<String> strList = new ArrayList<>();

}

