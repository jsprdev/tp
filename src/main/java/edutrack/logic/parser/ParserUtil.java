package edutrack.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import edutrack.commons.core.index.Index;
import edutrack.commons.util.StringUtil;
import edutrack.logic.parser.exceptions.ParseException;
import edutrack.model.group.Group;
import edutrack.model.person.Address;
import edutrack.model.person.Email;
import edutrack.model.person.Name;
import edutrack.model.person.Note;
import edutrack.model.person.Phone;
import edutrack.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_NAME_LENGTH_CONSTRAINTS = "Name too long (max %d characters).";
    public static final String MESSAGE_PHONE_LENGTH_CONSTRAINTS = "Phone number too long (max %d characters).";
    public static final String MESSAGE_ADDRESS_LENGTH_CONSTRAINTS = "Address too long (max %d characters).";
    public static final String MESSAGE_EMAIL_LENGTH_CONSTRAINTS = "Email too long (max %d characters).";
    public static final String MESSAGE_TAG_LENGTH_CONSTRAINTS = "Tag too long (max %d characters).";
    public static final String MESSAGE_GROUP_LENGTH_CONSTRAINTS = "Group name too long (max %d characters).";
    public static final String MESSAGE_NOTE_LENGTH_CONSTRAINTS = "Note too long (max %d characters).";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }

        if (trimmedName.length() > Name.MAX_NAME_LENGTH) {
            throw new ParseException(String.format(MESSAGE_NAME_LENGTH_CONSTRAINTS, Name.MAX_NAME_LENGTH));
        }

        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }

        if (trimmedPhone.length() > Phone.MAX_PHONE_LENGTH) {
            throw new ParseException(String.format(MESSAGE_PHONE_LENGTH_CONSTRAINTS, Phone.MAX_PHONE_LENGTH));
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        // Check validity AFTER trimming - empty is OK, but whitespace-only original input should fail
        if (!address.isEmpty() && trimmedAddress.isEmpty()) {
            // Original had content but was only whitespace
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }

        if (trimmedAddress.length() > Address.MAX_ADDRESS_LENGTH) {
            throw new ParseException(String.format(MESSAGE_ADDRESS_LENGTH_CONSTRAINTS, Address.MAX_ADDRESS_LENGTH));
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }

        if (trimmedEmail.length() > Email.MAX_EMAIL_LENGTH) {
            throw new ParseException(String.format(MESSAGE_EMAIL_LENGTH_CONSTRAINTS, Email.MAX_EMAIL_LENGTH));
        }

        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }

        if (trimmedTag.length() > Tag.MAX_TAG_LENGTH) {
            throw new ParseException(String.format(MESSAGE_TAG_LENGTH_CONSTRAINTS, Tag.MAX_TAG_LENGTH));
        }

        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String group} into a {@code Group}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code group} is invalid.
     */
    public static Group parseGroup(String group) throws ParseException {
        requireNonNull(group);
        String trimmedGroup = group.trim();
        if (!Group.isValidGroupName(trimmedGroup)) {
            throw new ParseException(Group.MESSAGE_CONSTRAINTS);
        }

        if (trimmedGroup.length() > Group.MAX_GROUP_LENGTH) {
            throw new ParseException(String.format(MESSAGE_GROUP_LENGTH_CONSTRAINTS, Group.MAX_GROUP_LENGTH));
        }

        return new Group(trimmedGroup);
    }

    /**
     * Parses {@code Collection<String> groups} into a {@code Set<Group>}.
     */
    public static Set<Group> parseGroups(Collection<String> groups) throws ParseException {
        requireNonNull(groups);
        final Set<Group> groupSet = new HashSet<>();
        for (String groupName : groups) {
            groupSet.add(parseGroup(groupName));
        }
        return groupSet;
    }

    /**
     * Parses a {@code String note} into a {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code note} is invalid.
     */
    public static Note parseNote(String note) throws ParseException {
        requireNonNull(note);
        String trimmedNote = note.trim();
        if (!Note.isValidNote(trimmedNote)) {
            throw new ParseException(Note.MESSAGE_CONSTRAINTS);
        }

        if (trimmedNote.length() > Note.MAX_NOTE_LENGTH) {
            throw new ParseException(String.format(MESSAGE_NOTE_LENGTH_CONSTRAINTS, Note.MAX_NOTE_LENGTH));
        }

        return new Note(trimmedNote);
    }

}
