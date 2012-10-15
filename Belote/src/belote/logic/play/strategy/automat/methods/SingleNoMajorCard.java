/*
 * Copyright (c) Dimitar Karamanov 2008-2010. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the source code must retain
 * the above copyright notice and the following disclaimer.
 *
 * This software is provided "AS IS," without a warranty of any kind.
 */
package belote.logic.play.strategy.automat.methods;

import belote.bean.Game;
import belote.bean.Player;
import belote.bean.pack.card.Card;
import belote.bean.pack.card.suit.Suit;
import belote.bean.pack.card.suit.SuitIterator;
import belote.logic.play.strategy.automat.methods.base.BaseMethod;

/**
 * SingleNoMajorCard class.
 * PlayCardMethod which implements the logic of playing the minimum by rank single no maximum left
 * no major suit card.
 * @author Dimitar Karamanov
 */
public final class SingleNoMajorCard extends BaseMethod {

    /**
     * Constructor.
     * @param game BelotGame instance class.
     */
    public SingleNoMajorCard(final Game game) {
        super(game);
    }

    /**
     * Returns player's card.
     * @param player who is on turn.
     * @return Card object instance or null.
     */
    public Card getPlayMethodCard(final Player player) {
        Card result = null;
        for (final SuitIterator iterator = Suit.iterator(); iterator.hasNext();) {
            final Suit suit = iterator.next();
            final int count = player.getCards().getSuitCount(suit);

            if (count == SINGLE_CARD_COUNT) {
                final Card minSuitCard = player.getCards().findMinSuitCard(suit);
                if (minSuitCard != null && !minSuitCard.isMajorCard() && !isMaxSuitCardLeft(minSuitCard, true)) {
                    if (result == null || result.compareRankTo(minSuitCard) > 0) {
                        result = minSuitCard;
                    }
                }
            }
        }
        return result;
    }
}