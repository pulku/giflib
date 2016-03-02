package pulku.giflib.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pulku.giflib.data.GifRepository;
import pulku.giflib.model.Gif;

import java.util.List;


@Controller
public class GifController {

    @Autowired
    private GifRepository gifRepository;

    @RequestMapping("/")
    public String listGifs(ModelMap modelMap) {
        List<Gif> allGifs = gifRepository.getAllGifs();
        modelMap.put("gifs", allGifs);
        return "home";
    }

    @RequestMapping("/gif/{name}")
    public String gifDetails(@PathVariable String name, ModelMap modelMap) {
        Gif gif = gifRepository.findByName(name);
        modelMap.put("gif", gif);
        return "gif-details";
    }

    @RequestMapping("/favorites")
    public String getFavorites(ModelMap modelMap) {
        List<Gif> gifs = gifRepository.findByFavorites();
        modelMap.put("gifs", gifs);
        return "favorites";
    }

    @RequestMapping(value = "/", params = "q")
    public String search(@RequestParam("q") String q, ModelMap modelMap) {
        List<Gif> gifs = gifRepository.findBySearch(q);
        modelMap.put("gifs", gifs);
        return "search";
    }

}
