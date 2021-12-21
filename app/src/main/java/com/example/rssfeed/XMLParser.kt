package com.example.rssfeed

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.net.URL

class XMLParser {
    private val feed = ArrayList<Post>()

     // Use these variables to create instances of the of the data we want to get
    private var text = ""
    private var title = ""

    fun parseRSS(): ArrayList<Post> {
        try{
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            val url = URL("https://stackoverflow.com/feeds")
            parser.setInput(url.openStream(), null) // To take the data from the url
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when(eventType) {
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("title", true) -> {
                            title = text
                            feed.add(Post(title))
                        }
                        else -> {}
                    }
                    else -> {}
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
       return feed
    }
}