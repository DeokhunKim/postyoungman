package com.gift.server.translate;

import com.gift.server.gift.Gift;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Delegate;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

@Data
@ToString
public class TranslateBundle {
    @Delegate
    private Deque<String> strList = new LinkedList<>();

}

