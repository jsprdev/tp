package edutrack.logic.parser;

import static edutrack.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static edutrack.testutil.Assert.assertThrows;
import static edutrack.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import edutrack.logic.parser.exceptions.ParseException;
import edutrack.model.group.Group;
import edutrack.model.person.Address;
import edutrack.model.person.Email;
import edutrack.model.person.Name;
import edutrack.model.person.Note;
import edutrack.model.person.Phone;
import edutrack.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+1";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_GROUP = "";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_GROUP_1 = "CS2103T";
    private static final String VALID_GROUP_2 = "Tutorial-01";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseGroup_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGroup(null));
    }

    @Test
    public void parseGroup_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGroup(INVALID_GROUP));
    }

    @Test
    public void parseGroup_validValueWithoutWhitespace_returnsGroup() throws Exception {
        Group expectedGroup = new Group(VALID_GROUP_1);
        assertEquals(expectedGroup, ParserUtil.parseGroup(VALID_GROUP_1));
    }

    @Test
    public void parseGroup_validValueWithWhitespace_returnsTrimmedGroup() throws Exception {
        String groupWithWhitespace = WHITESPACE + VALID_GROUP_1 + WHITESPACE;
        Group expectedGroup = new Group(VALID_GROUP_1);
        assertEquals(expectedGroup, ParserUtil.parseGroup(groupWithWhitespace));
    }

    @Test
    public void parseGroups_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGroups(null));
    }

    @Test
    public void parseGroups_collectionWithInvalidGroups_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGroups(Arrays.asList(VALID_GROUP_1, INVALID_GROUP)));
    }

    @Test
    public void parseGroups_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseGroups(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseGroups_collectionWithValidGroups_returnsGroupSet() throws Exception {
        Set<Group> actualGroupSet = ParserUtil.parseGroups(Arrays.asList(VALID_GROUP_1, VALID_GROUP_2));
        Set<Group> expectedGroupSet = new HashSet<>(Arrays.asList(new Group(VALID_GROUP_1), new Group(VALID_GROUP_2)));
        assertEquals(expectedGroupSet, actualGroupSet);
    }

    @Test
    public void parseName_exceedsMaxLength_throwsParseException() {
        String tooLongName = "a".repeat(Name.MAX_NAME_LENGTH + 1);
        assertThrows(ParseException.class, () -> ParserUtil.parseName(tooLongName));
    }

    @Test
    public void parsePhone_exceedsMaxLength_throwsParseException() {
        String tooLongPhone = "1".repeat(Phone.MAX_PHONE_LENGTH + 1);
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(tooLongPhone));
    }

    @Test
    public void parseEmail_exceedsMaxLength_throwsParseException() {
        String tooLongEmail = "a".repeat(Email.MAX_EMAIL_LENGTH + 1) + "@example.com";
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(tooLongEmail));
    }

    @Test
    public void parseAddress_exceedsMaxLength_throwsParseException() {
        String tooLongAddress = "a".repeat(Address.MAX_ADDRESS_LENGTH + 1);
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(tooLongAddress));
    }

    @Test
    public void parseTag_exceedsMaxLength_throwsParseException() {
        String tooLongTag = "a".repeat(Tag.MAX_TAG_LENGTH + 1);
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(tooLongTag));
    }

    @Test
    public void parseGroup_exceedsMaxLength_throwsParseException() {
        String tooLongGroup = "a".repeat(Group.MAX_GROUP_LENGTH + 1);
        assertThrows(ParseException.class, () -> ParserUtil.parseGroup(tooLongGroup));
    }

    @Test
    public void parseNote_exceedsMaxLength_throwsParseException() {
        String tooLongNote = "a".repeat(Note.MAX_NOTE_LENGTH + 1);
        assertThrows(ParseException.class, () -> ParserUtil.parseNote(tooLongNote));
    }

    @Test
    public void parseName_atMaxLength_returnsName() throws Exception {
        String nameAtMax = "a".repeat(Name.MAX_NAME_LENGTH);
        Name expectedName = new Name(nameAtMax);
        assertEquals(expectedName, ParserUtil.parseName(nameAtMax));
    }

    @Test
    public void parsePhone_atMaxLength_returnsPhone() throws Exception {
        String phoneAtMax = "1".repeat(Phone.MAX_PHONE_LENGTH);
        Phone expectedPhone = new Phone(phoneAtMax);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneAtMax));
    }

    @Test
    public void parseEmail_atMaxLength_returnsEmail() throws Exception {
        // Create valid email at max length (e.g., "a@" + "b".repeat(remaining))
        String emailAtMax = "a".repeat(Email.MAX_EMAIL_LENGTH - 12) + "@example.com";
        Email expectedEmail = new Email(emailAtMax);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailAtMax));
    }

    @Test
    public void parseAddress_atMaxLength_returnsAddress() throws Exception {
        String addressAtMax = "a".repeat(Address.MAX_ADDRESS_LENGTH);
        Address expectedAddress = new Address(addressAtMax);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressAtMax));
    }

    @Test
    public void parseTag_atMaxLength_returnsTag() throws Exception {
        String tagAtMax = "a".repeat(Tag.MAX_TAG_LENGTH);
        Tag expectedTag = new Tag(tagAtMax);
        assertEquals(expectedTag, ParserUtil.parseTag(tagAtMax));
    }

    @Test
    public void parseGroup_atMaxLength_returnsGroup() throws Exception {
        String groupAtMax = "a".repeat(Group.MAX_GROUP_LENGTH);
        Group expectedGroup = new Group(groupAtMax);
        assertEquals(expectedGroup, ParserUtil.parseGroup(groupAtMax));
    }

    @Test
    public void parseNote_atMaxLength_returnsNote() throws Exception {
        String noteAtMax = "a".repeat(Note.MAX_NOTE_LENGTH);
        Note expectedNote = new Note(noteAtMax);
        assertEquals(expectedNote, ParserUtil.parseNote(noteAtMax));
    }
}
