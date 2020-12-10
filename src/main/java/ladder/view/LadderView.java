package ladder.view;

import ladder.domain.game.Name;
import ladder.domain.game.Names;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created By mand2 on 2020-12-09.
 */
public class LadderView {

    private final Map<Name, Name> resultOfLadder;


    private LadderView(Map<Name, Name> resultOfLadder) {
        this.resultOfLadder = resultOfLadder;
    }

    public static LadderView of(Names participants, List<Name> moveResults) {
        return new LadderView(createLadderResult(participants, moveResults));
    }

    private static Map<Name, Name> createLadderResult(Names participants, List<Name> moveResults) {
        return IntStream.range(0, participants.getParticipantNum())
                .boxed()
                .collect(Collectors.toMap(
                        participants::getParticipantName,
                        moveResults::get,
                        (u1, u2) -> u1,
                        HashMap::new
                ));
    }

    public Name getResultOfOneParticipant(Name participant) {
        return this.resultOfLadder.get(participant);
    }

    public Map<Name, Name> getResultOfAll() {
        return Collections.unmodifiableMap(this.resultOfLadder);
    }

}