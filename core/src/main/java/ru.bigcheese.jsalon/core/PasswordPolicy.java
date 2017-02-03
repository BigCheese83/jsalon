package ru.bigcheese.jsalon.core;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordPolicy {

    public static final PasswordPolicy DEFAULT;

    private Pattern pattern;
    private List<String> messages = new ArrayList<>();

    private PasswordPolicy(String pattern, List<String> messages) {
        this.pattern = Pattern.compile(pattern);
        this.messages = messages;
    }

    public List<String> getPolicy() {
        return ImmutableList.copyOf(messages);
    }

    public boolean check(String password) {
        if (!Strings.isNullOrEmpty(password)) {
            Matcher matcher = pattern.matcher(password);
            return matcher.matches();
        }
        return false;
    }

    static {
        DEFAULT = new PasswordPolicy("((?=\\S+$).{6,})", Lists.newArrayList(
                "no whitespace allowed",
                "length at least 6 characters"
        ));
    }
}
