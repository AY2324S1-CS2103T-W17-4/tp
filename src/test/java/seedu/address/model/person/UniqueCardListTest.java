package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCards.CS1101S;
import static seedu.address.testutil.TypicalCards.CS2100;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.CardNotFoundException;
import seedu.address.model.person.exceptions.DuplicateCardException;
import seedu.address.testutil.CardBuilder;

public class UniqueCardListTest {

    private final UniqueCardList uniqueCardList = new UniqueCardList();

    @Test
    public void contains_nullCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.contains(null));
    }

    @Test
    public void contains_cardNotInList_returnsFalse() {
        assertFalse(uniqueCardList.contains(CS2100));
    }

    @Test
    public void contains_cardInList_returnsTrue() {
        uniqueCardList.add(CS2100);
        assertTrue(uniqueCardList.contains(CS2100));
    }

    @Test
    public void add_nullCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.add(null));
    }

    @Test
    public void add_duplicateCard_throwsDuplicateCardException() {
        uniqueCardList.add(CS2100);
        assertThrows(DuplicateCardException.class, () -> uniqueCardList.add(CS2100));
    }

    @Test
    public void setCard_nullTargetCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.setCard(null, CS2100));
    }

    @Test
    public void setCard_nullEditedCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.setCard(CS2100, null));
    }

    @Test
    public void setCard_targetCardNotInList_throwsCardNotFoundException() {
        assertThrows(CardNotFoundException.class, () -> uniqueCardList.setCard(CS2100, CS2100));
    }

    @Test
    public void setCard_editedCardIsSameCard_success() {
        uniqueCardList.add(CS2100);
        uniqueCardList.setCard(CS2100, CS2100);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        expectedUniqueCardList.add(CS2100);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCard_editedCardHasSameIdentity_success() {
        uniqueCardList.add(CS2100);
        Card editedCS2100 = new CardBuilder(CS2100).withQuestion("Is the sun green?").build();
        uniqueCardList.setCard(CS2100, editedCS2100);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        expectedUniqueCardList.add(editedCS2100);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCard_editedCardHasDifferentIdentity_success() {
        uniqueCardList.add(CS2100);
        uniqueCardList.setCard(CS2100, CS1101S);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        expectedUniqueCardList.add(CS1101S);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCard_editedCardHasNonUniqueIdentity_throwsDuplicateCardException() {
        uniqueCardList.add(CS2100);
        uniqueCardList.add(CS1101S);
        assertThrows(DuplicateCardException.class, () -> uniqueCardList.setCard(CS2100, CS1101S));
    }

    @Test
    public void remove_nullCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.remove(null));
    }

    @Test
    public void remove_cardDoesNotExist_throwsCardNotFoundException() {
        assertThrows(CardNotFoundException.class, () -> uniqueCardList.remove(CS2100));
    }

    @Test
    public void remove_existingCard_removesCard() {
        uniqueCardList.add(CS2100);
        uniqueCardList.remove(CS2100);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCards_nullUniqueCardList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.setCards((UniqueCardList) null));
    }

    @Test
    public void setCards_uniqueCardList_replacesOwnListWithProvidedUniqueCardList() {
        uniqueCardList.add(CS2100);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        expectedUniqueCardList.add(CS1101S);
        uniqueCardList.setCards(expectedUniqueCardList);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCards_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.setCards((List<Card>) null));
    }

    @Test
    public void setCards_list_replacesOwnListWithProvidedList() {
        uniqueCardList.add(CS2100);
        List<Card> cardList = Collections.singletonList(CS1101S);
        uniqueCardList.setCards(cardList);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        expectedUniqueCardList.add(CS1101S);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCards_listWithDuplicateCards_throwsDuplicateCardException() {
        List<Card> listWithDuplicateCards = Arrays.asList(CS2100, CS2100);
        assertThrows(DuplicateCardException.class, () -> uniqueCardList.setCards(listWithDuplicateCards));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueCardList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueCardList.asUnmodifiableObservableList().toString(), uniqueCardList.toString());
    }
}
