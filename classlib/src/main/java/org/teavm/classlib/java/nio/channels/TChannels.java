/*
 *  Copyright 2023 Jonathan Coates.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.teavm.classlib.java.nio.channels;

import java.io.IOException;
import java.util.Objects;
import org.teavm.classlib.java.io.*;
import org.teavm.classlib.java.nio.TByteBuffer;
import org.teavm.classlib.java.nio.charset.TCharset;
import org.teavm.classlib.java.nio.charset.TCharsetDecoder;
import org.teavm.classlib.java.nio.charset.TCharsetEncoder;

public final class TChannels {
    private TChannels() {
    }

    public static TInputStream newInputStream(TReadableByteChannel channel) {
        Objects.requireNonNull(channel, "channel");
        return new ChannelInputStream(channel);
    }

    public static TOutputStream newOutputStream(TWritableByteChannel channel) {
        Objects.requireNonNull(channel, "channel");
        return new ChannelOutputStream(channel);
    }

    public static TReader newReader(TReadableByteChannel channel, String charset) {
        return newReader(channel, TCharset.forName(charset));
    }

    public static TReader newReader(TReadableByteChannel channel, TCharset charset) {
        return newReader(channel, charset.newDecoder(), -1);
    }

    public static TReader newReader(TReadableByteChannel channel, TCharsetDecoder dec, int minBufferCap) {
        return new TInputStreamReader(newInputStream(channel), dec);
    }

    public static TWriter newWriter(TWritableByteChannel channel, String charset) {
        return newWriter(channel, TCharset.forName(charset));
    }

    public static TWriter newWriter(TWritableByteChannel channel, TCharset charset) {
        return newWriter(channel, charset.newEncoder(), -1);
    }

    public static TWriter newWriter(TWritableByteChannel channel, TCharsetEncoder dec, int minBufferCap) {
        return new TOutputStreamWriter(newOutputStream(channel), dec);
    }

    private static class ChannelInputStream extends TInputStream {
        private final TReadableByteChannel channel;
        private final TByteBuffer oneByte = TByteBuffer.allocateDirect(1);

        private ChannelInputStream(TReadableByteChannel channel) {
            this.channel = channel;
        }

        @Override
        public int read() throws IOException {
            oneByte.position(0);
            if (channel.read(oneByte) <= 0) {
                return -1;
            }
            return oneByte.get(0) & 0xFF;
        }

        @Override
        public int read(byte[] buffer, int off, int len) throws IOException {
            return channel.read(TByteBuffer.wrap(buffer, off, len));
        }
    }

    private static class ChannelOutputStream extends TOutputStream {
        private final TByteBuffer oneByte;
        private final TWritableByteChannel channel;

        public ChannelOutputStream(TWritableByteChannel channel) {
            this.channel = channel;
            oneByte = TByteBuffer.allocateDirect(1);
        }

        @Override
        public void write(int b) throws IOException {
            oneByte.position(0).put(0, (byte) b).limit(1);
            channel.write(oneByte);
        }

        @Override
        public void write(byte[] buffer, int off, int len) throws IOException {
            channel.write(TByteBuffer.wrap(buffer, off, len));
        }
    }
}
