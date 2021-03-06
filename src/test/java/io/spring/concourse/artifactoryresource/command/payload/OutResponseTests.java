/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.concourse.artifactoryresource.command.payload;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link OutResponse}.
 *
 * @author Phillip Webb
 */
@RunWith(SpringRunner.class)
@JsonTest
public class OutResponseTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	private JacksonTester<OutResponse> json;

	@Test
	public void createWhenVersionIsNullShouldThrowException() throws Exception {
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Version must not be null");
		new InResponse(null, null);
	}

	@Test
	public void writeShouldSerialize() throws Exception {
		List<Metadata> metadata = new ArrayList<>();
		metadata.add(new Metadata("foo", "bar"));
		metadata.add(new Metadata("bin", "bag"));
		OutResponse response = new OutResponse(new Version("1234"), metadata);
		assertThat(this.json.write(response)).isEqualToJson("out-response.json");
	}

	@Test
	public void writeWhenMetadataIsNullShouldSerialize() throws Exception {
		OutResponse response = new OutResponse(new Version("1234"), null);
		assertThat(this.json.write(response))
				.isEqualToJson("out-response-without-metadata.json");
	}

}
