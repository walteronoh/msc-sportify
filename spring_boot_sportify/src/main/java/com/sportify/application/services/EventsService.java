package com.sportify.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sportify.application.data.entity.event.Game;
import com.sportify.application.data.entity.event.Series;
import com.sportify.application.data.entity.event.Sport;
import com.sportify.application.data.repository.event.GameRepository;
import com.sportify.application.data.repository.event.SeriesRepository;
import com.sportify.application.data.repository.event.SportRepository;

@Service
public class EventsService {
    public final GameRepository gameRepository;
    public final SeriesRepository seriesRepository;
    public final SportRepository sportRepository;

    public EventsService(GameRepository gameRepository, SeriesRepository seriesRepository,
            SportRepository sportRepository) {
        this.gameRepository = gameRepository;
        this.seriesRepository = seriesRepository;
        this.sportRepository = sportRepository;
    }

    public void saveGame(Game game) {
        if(game == null) {
            System.err.println("Game is null.");
            return;
        }
        gameRepository.save(game);
    }

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    public List<Series> findAllSeries() {
        return seriesRepository.findAll();
    }

    public List<Sport> findAllSports() {
        return sportRepository.findAll();
    }
}
