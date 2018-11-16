package com.sda.servlets.links;

import com.sda.servlets.users.User;
import com.sda.servlets.users.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LinksService {

    //EAGER SINGLETON
    private static LinksService instance = new LinksService();

    public static LinksService instanceOf(){
        return instance;
    }
    // LAZY SINGLETON
//    public static LinksService instanceOf() {
//        if (instance == null) {
//            instance = new LinksService();
//        }
//        return instance;
//    }

    private List<Link> links;
    private Integer nextId;

    private LinksService() {
        this.links = new ArrayList<>();
        this.nextId = 1;

        save(new Link("https://www.google.pl", "Google"));
        save(new Link("https://www.onet.pl", "Onet"));
        save(new Link("https://www.wp.pl", "Wp"));
    }

    public List<Link> findAll(){
        return new ArrayList<>(links);
    }

//    public void save(Link link) {
//        if (link.getId() != null) {
//            links.stream()
//                    .filter(e -> e.getId().equals(link.getId()))
//                    .findFirst()
//                    .ifPresent(e -> {
//                        e.setUrl(link.getUrl());
//                        e.setText(link.getText());
//                    });
//        } else {
//            link.setId(nextId++);
//            links.add(link);
//        }
//    }

    public void save(Link linkObject){
        if (linkObject.getId() == null) {
            linkObject.setId(nextId++);
            links.add(linkObject);
        }else {
            links.stream()
                    .filter(e->e.getId().equals(linkObject.getId()))
                    .findAny()
                    .ifPresent(e->{
                        e.setUrl(linkObject.getUrl());
                        e.setText(linkObject.getText());
                    });
        }
    }

    public Optional<Link> findById(int id) {
        return links.stream()
                .filter(links -> links.getId().equals(id))
                .findFirst();
    }

    public List<Link> findByQuery(String query) {
        List<Link> linksToReturn = new ArrayList<>();
        for (Link link : links) {
            String linkRepresentation = link.getText();
            if (linkRepresentation.contains(query)) {
                linksToReturn.add(link);
            }
        }
        return linksToReturn;
    }
//szukamy, pobieramy pozycję, usuwamy
    public void delete(Integer id) {
        this.links.stream()
                .filter(e->e.getId().equals(id))
                .findAny()
                .ifPresent(e-> {
                    int index = links.indexOf(e);
                    links.remove(index);
                });
    }

}
