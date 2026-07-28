typedef struct {
    char **dict;
    int size;
} MagicDictionary;

MagicDictionary* magicDictionaryCreate() {
    MagicDictionary* obj = (MagicDictionary*)malloc(sizeof(MagicDictionary));
    obj->dict = NULL;
    obj->size = 0;
    return obj;
}

void magicDictionaryBuildDict(MagicDictionary* obj, char** dictionary, int dictionarySize) {
    obj->size = dictionarySize;
    obj->dict = (char**)malloc(sizeof(char*) * dictionarySize);

    for (int i = 0; i < dictionarySize; i++) {
        int len = strlen(dictionary[i]);
        obj->dict[i] = (char*)malloc(len + 1);
        strcpy(obj->dict[i], dictionary[i]);
    }
}

bool magicDictionarySearch(MagicDictionary* obj, char* searchWord) {
    int len = strlen(searchWord);

    for (int i = 0; i < obj->size; i++) {
        if ((int)strlen(obj->dict[i]) != len)
            continue;

        int diff = 0;
        for (int j = 0; j < len; j++) {
            if (obj->dict[i][j] != searchWord[j])
                diff++;
            if (diff > 1)
                break;
        }

        if (diff == 1)
            return true;
    }

    return false;
}

void magicDictionaryFree(MagicDictionary* obj) {
    for (int i = 0; i < obj->size; i++)
        free(obj->dict[i]);

    free(obj->dict);
    free(obj);
}