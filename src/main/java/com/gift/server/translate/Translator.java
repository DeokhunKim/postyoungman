package com.gift.server.translate;

import java.util.List;

public interface Translator {
    TranslateBundle eng2kor(TranslateBundle engStrList);
    TranslateBundle eng2kor_exceptHtml(TranslateBundle engStrList);
}
