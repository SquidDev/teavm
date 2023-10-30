/*
 *  Copyright 2023 Alexey Andreev.
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
package org.teavm.backend.javascript.intrinsics.ref;

import java.io.IOException;
import org.teavm.backend.javascript.codegen.SourceWriter;
import org.teavm.backend.javascript.spi.Generator;
import org.teavm.backend.javascript.spi.GeneratorContext;
import org.teavm.backend.javascript.templating.JavaScriptTemplate;
import org.teavm.backend.javascript.templating.JavaScriptTemplateFactory;
import org.teavm.model.MethodReference;

public class ReferenceQueueGenerator implements Generator {
    private JavaScriptTemplate template;

    @Override
    public void generate(GeneratorContext context, SourceWriter writer, MethodReference methodRef) throws IOException {
        ensureTemplate(context);
        switch (methodRef.getName()) {
            case "<init>":
                template.builder("init").withContext(context).build().write(writer, 0);
                break;
            case "poll":
                template.builder("poll").withContext(context).build().write(writer, 0);
                break;
        }
    }

    private void ensureTemplate(GeneratorContext context) throws IOException {
        if (template == null) {
            template = new JavaScriptTemplateFactory(context.getClassLoader(), context.getClassSource())
                    .createFromResource("org/teavm/classlib/java/lang/ref/ReferenceQueue.js");
        }
    }
}
