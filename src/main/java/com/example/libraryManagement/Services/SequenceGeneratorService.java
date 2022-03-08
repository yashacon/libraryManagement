package com.example.libraryManagement.Services;

import com.example.libraryManagement.Models.DatabaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class SequenceGeneratorService {

    @Autowired
    private MongoOperations mongo;

    public long generateSequence(String seqName) {
        Query query = new Query(Criteria.where("_id").is(seqName));

        Update update = new Update();
        update.inc("seq", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        options.upsert(true);
        DatabaseSequence counter = mongo.findAndModify(query,update,options,
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
