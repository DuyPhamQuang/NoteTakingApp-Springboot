CREATE TABLE EventParticipant (
    PRIMARY KEY (EventID, ParticipantID),
    FOREIGN KEY (EventID) REFERENCES Event(EventKey),
    FOREIGN KEY (ParticipantID) REFERENCES Participant(ParticipantKey)
);