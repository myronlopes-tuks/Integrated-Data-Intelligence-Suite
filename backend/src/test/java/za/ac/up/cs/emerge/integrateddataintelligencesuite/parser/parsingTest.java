package za.ac.up.cs.emerge.integrateddataintelligencesuite.parser;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import za.ac.up.cs.emerge.integrateddataintelligencesuite.importer.DataSource;
import za.ac.up.cs.emerge.integrateddataintelligencesuite.parser.exceptions.InvalidRequestException;
import za.ac.up.cs.emerge.integrateddataintelligencesuite.parser.request.ParseImportedDataRequest;
import za.ac.up.cs.emerge.integrateddataintelligencesuite.parser.response.ParseImportedDataResponse;

//import org.springframework.util.Assert;
import za.ac.up.cs.emerge.integrateddataintelligencesuite.importer.DataSource;
import za.ac.up.cs.emerge.integrateddataintelligencesuite.parser.exceptions.InvalidRequestException;
import za.ac.up.cs.emerge.integrateddataintelligencesuite.parser.request.ParseImportedDataRequest;

import za.ac.up.cs.emerge.integrateddataintelligencesuite.parser.rri.TwitterExtractor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class parsingTest {
    //private TwitterExtractor twitterMock;
    private ParsingServiceImpl parsingServiceTest= new ParsingServiceImpl();

    //@Before
    /*public void setup(){
        parsingServiceTest = new ParsingServiceImpl();
        twitterMock = mock(TwitterExtractor.class);
        parsingServiceTest.t
    }*/

    @Test
    @DisplayName("When the request object is null")
    public void testParsingImportedDataNUll(){
        //parsingServiceTest = new ParsingServiceImpl();
        Assert.assertThrows(InvalidRequestException.class, ()->parsingServiceTest.parseImportedData(null));
    }


    @Test
    @DisplayName("When the datasource is null")
    public void testDataSourceNUll(){
        //parsingServiceTest = new ParsingServiceImpl();
        ParseImportedDataRequest request = new ParseImportedDataRequest(null,"{}");
        Assert.assertThrows(InvalidRequestException.class, ()->parsingServiceTest.parseImportedData(request));
    }

    @Test
    @DisplayName("When the json string is null")
    public void testJsonStringNUll(){
        //parsingServiceTest = new ParsingServiceImpl();
        ParseImportedDataRequest request = new ParseImportedDataRequest(DataSource.TWITTER,null);
        Assert.assertThrows(InvalidRequestException.class, ()->parsingServiceTest.parseImportedData(request));
    }

    @Test
    @DisplayName("When a valid json string is entered")
    public void validparseImportedDataRequest() throws JSONException, InvalidRequestException {
        ParseImportedDataRequest Test = new ParseImportedDataRequest(DataSource.TWITTER, "{\"statuses\":[{\"created_at\":\"Sun Jun 06 17:41:19 +0000 2021\",\"id\":1401595070827143176,\"id_str\":\"1401595070827143176\",\"text\":\"@Nkoskhodola_23 Lols he he he I wish we win. Lols u\\u2019ll feel is coz if we get chucked out we\\u2019ll know u too\",\"truncated\":false,\"entities\":{\"hashtags\":[],\"symbols\":[],\"user_mentions\":[{\"screen_name\":\"Nkoskhodola_23\",\"name\":\"Mandisa\\ud83d\\udc99\",\"id\":3422365383,\"id_str\":\"3422365383\",\"indices\":[0,15]}],\"urls\":[]},\"metadata\":{\"iso_language_code\":\"en\",\"result_type\":\"recent\"},\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\/download\\/iphone\\\" rel=\\\"nofollow\\\"\\u003eTwitter for iPhone\\u003c\\/a\\u003e\",\"in_reply_to_status_id\":1401511558853890055,\"in_reply_to_status_id_str\":\"1401511558853890055\",\"in_reply_to_user_id\":3422365383,\"in_reply_to_user_id_str\":\"3422365383\",\"in_reply_to_screen_name\":\"Nkoskhodola_23\",\"user\":{\"id\":935154685941616641,\"id_str\":\"935154685941616641\",\"name\":\"Gaffit\",\"screen_name\":\"Wandile_Ntini\",\"location\":\"eSwatini, South Africa\",\"description\":\"We Promise To Win\",\"url\":null,\"entities\":{\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":3459,\"friends_count\":1306,\"listed_count\":0,\"created_at\":\"Mon Nov 27 14:33:46 +0000 2017\",\"favourites_count\":2305,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"verified\":false,\"statuses_count\":7961,\"lang\":null,\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"F5F8FA\",\"profile_background_image_url\":null,\"profile_background_image_url_https\":null,\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_images\\/1322179791987052544\\/6rEo0oj9_normal.jpg\",\"profile_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_images\\/1322179791987052544\\/6rEo0oj9_normal.jpg\",\"profile_banner_url\":\"https:\\/\\/pbs.twimg.com\\/profile_banners\\/935154685941616641\\/1563790712\",\"profile_link_color\":\"1DA1F2\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"has_extended_profile\":true,\"default_profile\":true,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null,\"translator_type\":\"none\",\"withheld_in_countries\":[]},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":0,\"favorite_count\":1,\"favorited\":false,\"retweeted\":false,\"lang\":\"en\"},{\"created_at\":\"Sun Jun 06 17:40:21 +0000 2021\",\"id\":1401594827842625538,\"id_str\":\"1401594827842625538\",\"text\":\"\\u2764\\u2764\\u2764\\u2764 https:\\/\\/t.co\\/lnYnKmo6qQ\",\"truncated\":false,\"entities\":{\"hashtags\":[],\"symbols\":[],\"user_mentions\":[],\"urls\":[{\"url\":\"https:\\/\\/t.co\\/lnYnKmo6qQ\",\"expanded_url\":\"https:\\/\\/twitter.com\\/kihora_\\/status\\/1401465071721668613\",\"display_url\":\"twitter.com\\/kihora_\\/status\\u2026\",\"indices\":[5,28]}]},\"metadata\":{\"iso_language_code\":\"und\",\"result_type\":\"recent\"},\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\/download\\/android\\\" rel=\\\"nofollow\\\"\\u003eTwitter for Android\\u003c\\/a\\u003e\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":2989319806,\"id_str\":\"2989319806\",\"name\":\"I'm the Wave\\ud83c\\udf0a\\ud83d\\udd25\\ud83d\\udc51\",\"screen_name\":\"Wandile_AM\",\"location\":\"Johannesburg\",\"description\":\"Model | \\n| Diploma in Business Management\\u23f3\\ud83d\\udcda\\nINFLUENCER!\\ud83d\\udcb0\\ud83d\\udcc8\\ud83d\\udcc9\",\"url\":null,\"entities\":{\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":297,\"friends_count\":30,\"listed_count\":0,\"created_at\":\"Wed Jan 21 08:28:56 +0000 2015\",\"favourites_count\":1448,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":true,\"verified\":false,\"statuses_count\":859,\"lang\":null,\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"C0DEED\",\"profile_background_image_url\":\"http:\\/\\/abs.twimg.com\\/images\\/themes\\/theme1\\/bg.png\",\"profile_background_image_url_https\":\"https:\\/\\/abs.twimg.com\\/images\\/themes\\/theme1\\/bg.png\",\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_images\\/1382779270284591113\\/9uIZt6K2_normal.jpg\",\"profile_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_images\\/1382779270284591113\\/9uIZt6K2_normal.jpg\",\"profile_banner_url\":\"https:\\/\\/pbs.twimg.com\\/profile_banners\\/2989319806\\/1503499546\",\"profile_link_color\":\"1DA1F2\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"has_extended_profile\":false,\"default_profile\":true,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null,\"translator_type\":\"none\",\"withheld_in_countries\":[]},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":true,\"quoted_status_id\":1401465071721668613,\"quoted_status_id_str\":\"1401465071721668613\",\"quoted_status\":{\"created_at\":\"Sun Jun 06 09:04:45 +0000 2021\",\"id\":1401465071721668613,\"id_str\":\"1401465071721668613\",\"text\":\"How it started       Vs       How it\\u2019s going https:\\/\\/t.co\\/ZukiuCP9JU\",\"truncated\":false,\"entities\":{\"hashtags\":[],\"symbols\":[],\"user_mentions\":[],\"urls\":[],\"media\":[{\"id\":1401464997801349122,\"id_str\":\"1401464997801349122\",\"indices\":[45,68],\"media_url\":\"http:\\/\\/pbs.twimg.com\\/media\\/E3MA_X8X0AIUyMz.jpg\",\"media_url_https\":\"https:\\/\\/pbs.twimg.com\\/media\\/E3MA_X8X0AIUyMz.jpg\",\"url\":\"https:\\/\\/t.co\\/ZukiuCP9JU\",\"display_url\":\"pic.twitter.com\\/ZukiuCP9JU\",\"expanded_url\":\"https:\\/\\/twitter.com\\/kihora_\\/status\\/1401465071721668613\\/photo\\/1\",\"type\":\"photo\",\"sizes\":{\"thumb\":{\"w\":150,\"h\":150,\"resize\":\"crop\"},\"small\":{\"w\":510,\"h\":680,\"resize\":\"fit\"},\"medium\":{\"w\":900,\"h\":1200,\"resize\":\"fit\"},\"large\":{\"w\":1536,\"h\":2048,\"resize\":\"fit\"}}}]},\"extended_entities\":{\"media\":[{\"id\":1401464997801349122,\"id_str\":\"1401464997801349122\",\"indices\":[45,68],\"media_url\":\"http:\\/\\/pbs.twimg.com\\/media\\/E3MA_X8X0AIUyMz.jpg\",\"media_url_https\":\"https:\\/\\/pbs.twimg.com\\/media\\/E3MA_X8X0AIUyMz.jpg\",\"url\":\"https:\\/\\/t.co\\/ZukiuCP9JU\",\"display_url\":\"pic.twitter.com\\/ZukiuCP9JU\",\"expanded_url\":\"https:\\/\\/twitter.com\\/kihora_\\/status\\/1401465071721668613\\/photo\\/1\",\"type\":\"photo\",\"sizes\":{\"thumb\":{\"w\":150,\"h\":150,\"resize\":\"crop\"},\"small\":{\"w\":510,\"h\":680,\"resize\":\"fit\"},\"medium\":{\"w\":900,\"h\":1200,\"resize\":\"fit\"},\"large\":{\"w\":1536,\"h\":2048,\"resize\":\"fit\"}}},{\"id\":1401464998250045441,\"id_str\":\"1401464998250045441\",\"indices\":[45,68],\"media_url\":\"http:\\/\\/pbs.twimg.com\\/media\\/E3MA_ZnWYAEsatc.jpg\",\"media_url_https\":\"https:\\/\\/pbs.twimg.com\\/media\\/E3MA_ZnWYAEsatc.jpg\",\"url\":\"https:\\/\\/t.co\\/ZukiuCP9JU\",\"display_url\":\"pic.twitter.com\\/ZukiuCP9JU\",\"expanded_url\":\"https:\\/\\/twitter.com\\/kihora_\\/status\\/1401465071721668613\\/photo\\/1\",\"type\":\"photo\",\"sizes\":{\"thumb\":{\"w\":150,\"h\":150,\"resize\":\"crop\"},\"small\":{\"w\":618,\"h\":680,\"resize\":\"fit\"},\"large\":{\"w\":1862,\"h\":2048,\"resize\":\"fit\"},\"medium\":{\"w\":1091,\"h\":1200,\"resize\":\"fit\"}}}]},\"metadata\":{\"iso_language_code\":\"und\",\"result_type\":\"recent\"},\"source\":\"\\u003ca href=\\\"http:\\/\\/twitter.com\\/download\\/iphone\\\" rel=\\\"nofollow\\\"\\u003eTwitter for iPhone\\u003c\\/a\\u003e\",\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":1212694922286878720,\"id_str\":\"1212694922286878720\",\"name\":\"Kiki\",\"screen_name\":\"kihora_\",\"location\":\"\",\"description\":\"Entrepreneur |Fashion Designer\\/Seamstress|founder creative director of IG: @Kihorafashion Kihorangoie@gmail.com\",\"url\":null,\"entities\":{\"description\":{\"urls\":[]}},\"protected\":false,\"followers_count\":1026,\"friends_count\":433,\"listed_count\":0,\"created_at\":\"Thu Jan 02 11:20:00 +0000 2020\",\"favourites_count\":2151,\"utc_offset\":null,\"time_zone\":null,\"geo_enabled\":false,\"verified\":false,\"statuses_count\":394,\"lang\":null,\"contributors_enabled\":false,\"is_translator\":false,\"is_translation_enabled\":false,\"profile_background_color\":\"F5F8FA\",\"profile_background_image_url\":null,\"profile_background_image_url_https\":null,\"profile_background_tile\":false,\"profile_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_images\\/1399103105854328834\\/8ahyWAk-_normal.jpg\",\"profile_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_images\\/1399103105854328834\\/8ahyWAk-_normal.jpg\",\"profile_banner_url\":\"https:\\/\\/pbs.twimg.com\\/profile_banners\\/1212694922286878720\\/1622979826\",\"profile_link_color\":\"1DA1F2\",\"profile_sidebar_border_color\":\"C0DEED\",\"profile_sidebar_fill_color\":\"DDEEF6\",\"profile_text_color\":\"333333\",\"profile_use_background_image\":true,\"has_extended_profile\":false,\"default_profile\":true,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null,\"translator_type\":\"none\",\"withheld_in_countries\":[]},\"geo\":null,\"coordinates\":null,\"place\":null,\"contributors\":null,\"is_quote_status\":false,\"retweet_count\":1898,\"favorite_count\":18146,\"favorited\":false,\"retweeted\":false,\"possibly_sensitive\":false,\"lang\":\"en\"},\"retweet_count\":0,\"favorite_count\":0,\"favorited\":false,\"retweeted\":false,\"possibly_sensitive\":false,\"lang\":\"und\"}],\"search_metadata\":{\"completed_in\":0.044,\"max_id\":1401595070827143176,\"max_id_str\":\"1401595070827143176\",\"next_results\":\"?max_id=1401594827842625537&q=wandile&count=2&include_entities=1\",\"query\":\"wandile\",\"refresh_url\":\"?since_id=1401595070827143176&q=wandile&include_entities=1\",\"count\":2,\"since_id\":0,\"since_id_str\":\"0\"}}");
        ParseImportedDataResponse resp = parsingServiceTest.parseImportedData(Test);
        Assert.assertNotNull(resp.getDataList());
    }

}
