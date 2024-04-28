package kz.madizhaksykeldi.crudshop.entity.dictionary;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {

    NEW("NEW"),
    PROCESSING("PROCESSING"),
    FINISHED("FINISHED"),
    CANCELED("CANCELED");

    private final String referenceName;
}