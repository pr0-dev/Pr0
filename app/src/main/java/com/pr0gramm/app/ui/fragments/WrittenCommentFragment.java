package com.pr0gramm.app.ui.fragments;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.pr0gramm.app.api.pr0gramm.response.Message;
import com.pr0gramm.app.api.pr0gramm.response.UserComments;
import com.pr0gramm.app.services.UserService;

import java.util.List;

import rx.Observable;

/**
 */
public class WrittenCommentFragment extends MessageInboxFragment {
    @Inject
    private UserService userService;

    @Override
    protected Observable<List<Message>> newMessageObservable() {
        String name = userService.getName().get();
        return getInboxService().getUserComments(name).map(userComments -> {
            //noinspection CodeBlock2Expr
            return Lists.transform(userComments.getComments(),
                    comment -> Message.of(userComments.getUser(), comment));
        });
    }

}
