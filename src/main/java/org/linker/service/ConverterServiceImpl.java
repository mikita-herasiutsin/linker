/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2016 Mikita Herasiutsin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.linker.service;

import org.linker.model.domain.Tag;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Type's converter.
 *
 * @since 1.0
 * @author Mikita Herasiutsin (mikita.herasiutsin@gmail.com)
 * @version $Id$
 */
@Service
public class ConverterServiceImpl implements ConverterService {
    /**
     * proof against offensive words (removed 'a', 'e', 'i', 'o' and 'u')
     * unambiguous (removed 'I', 'l', '1', 'O' and '0')
     *
     * Example output:
     * 123456789 <=> pgK8p
     */
    private static final String ALPHABET =
        "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ-_";
    /**
     * Scale of notation.
     */
    private static final Integer BASE = ALPHABET.length();

    @Override
    public String encode(Integer num) {
        StringBuilder str = new StringBuilder();
        while (num > 0) {
            str.insert(0, ALPHABET.charAt(num % BASE));
            num = num / BASE;
        }
        return str.toString();
    }

    @Override
    public int decode(final String str) {
        Integer num = 0;
        for (int i = 0; i < str.length(); i++) {
            num = num * BASE + ALPHABET.indexOf(str.charAt(i));
        }
        return num;
    }

    @Override
    public List<Tag> stringToTags(final String tagString) {
        if (tagString.isEmpty()) {
            return new ArrayList<>();
        }
        List<Tag> tags = new ArrayList<>();
        String[] tagsArray = tagString.split(" ");
        for (String tag : tagsArray) {
            tags.add(new Tag(tag));
        }
        return tags;
    }

    @Override
    public String tagsToString(final List<Tag> tags) {
        if (tags.isEmpty()){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Tag tag : tags) {
            builder.append(tag.getName()).append(" ");
        }
        builder.delete(builder.length() - 1, builder.length());
        return builder.toString();
    }
}
