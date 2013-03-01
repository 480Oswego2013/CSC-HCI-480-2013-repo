package edu.oswego.csc480_hci521_2013.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;

/**
 * PlaceHistoryMapper interface is used to attach all places which the
 * PlaceHistoryHandler should be aware of. This is done via the @WithTokenizers
 * annotation or by extending PlaceHistoryMapperWithFactory and creating a
 * separate TokenizerFactory.
 */

// @WithTokenizers( { BlahPlace.Tokenizer.class, Blah2Place.Tokenizer.class })

public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {}
