package neetcode75;

import java.util.*;
//https://leetcode.com/problems/design-twitter/
public class TwitterDesign {
    Map<Integer, Set<Integer>> followerMap;
    Map<Integer, LinkedList<Tweet>> tweetMap;
    int count = 1;

    public void Twitter() {
        followerMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        followerMap.putIfAbsent(userId, new HashSet<>());
        followerMap.get(userId).add(userId);

        tweetMap.putIfAbsent(userId, new LinkedList<>());
        Tweet newTweet = new Tweet(tweetId, count++);
        tweetMap.get(userId).add(newTweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        if (!followerMap.containsKey(userId))
            return new LinkedList<>();
        PriorityQueue<Tweet> feedMaxHeap = new PriorityQueue<>((tweet1, tweet2) -> tweet2.time - tweet1.time);
        for (int followerId : followerMap.get(userId)) {
            if (tweetMap.containsKey(followerId))
                feedMaxHeap.addAll(tweetMap.get(followerId));
        }
        List<Integer> tweets = new LinkedList<>();
        while (!feedMaxHeap.isEmpty() && tweets.size() < 10) {
            tweets.add(feedMaxHeap.poll().id);
        }
        return tweets;
    }

    public void follow(int followerId, int followeeId) {
        followerMap.putIfAbsent(followerId, new HashSet<>());
        followerMap.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        followerMap.putIfAbsent(followerId, new HashSet<>());
        Set<Integer> followers = followerMap.get(followerId);
        followers.remove(followeeId);
        followerMap.put(followerId, followers);
    }
}


class Tweet {
    int id;
    int time;

    public Tweet(int id, int time) {
        this.id = id;
        this.time = time;
    }
}

