package com.usta.inventory_b.controllers;

import com.usta.inventory_b.entities.ProductEntity;
import com.usta.inventory_b.models.services.ProductService;
import jakarta.validation.Valid;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String viewProducts(Model model,
                               @RequestParam(required = false) Long idProd,
                               @RequestParam(required = false) String nombreProd,
                               @RequestParam(required = false) String categoria,
                               @RequestParam(required = false) String marca) {
        List<ProductEntity> products = productService.filterProducts(nombreProd,categoria,marca,idProd);
        model.addAttribute("activeMenu", "products");
        model.addAttribute("products", products);
        model.addAttribute("idProd", idProd);
        model.addAttribute("nombreProd", nombreProd);
        model.addAttribute("categoria", categoria);
        model.addAttribute("marca", marca);
        return "products/listProducts";
    }

    /* -------------------------------------------- */

    @GetMapping("/new")
    public String ShowCreateForm(Model model) {
        model.addAttribute("title", "Create Product");
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("activeMenu", "products");
        return "products/newProduct";
    }

    @PostMapping("/new")
    public String saveProduct(@Valid @ModelAttribute("product")
                                  ProductEntity product,
                              BindingResult result,
                              @RequestParam("foto")MultipartFile foto,
                              Model model,
                              RedirectAttributes redirectAttributes){
        model.addAttribute("activeMenu","products");
        model.addAttribute("title","Create Product");
        if (foto == null || foto.isEmpty()){
            result.rejectValue("fotoUrl","foto.required", "You must upload a product image");
        }

        if (result.hasErrors()){
            return "products/newProduct";
        }
        String imageUrl;
        try {
            imageUrl = saveImage(foto);
        } catch (IOException e) {
            model.addAttribute("ErrorImagen", e.getMessage());
            return "products/newProduct";
        }

        if (imageUrl == null || imageUrl.isBlank()){
            model.addAttribute("ErrorImagen", "The image could not be uploaded");
            return "products/newProduct";
        }

        product.setFotoUrl(imageUrl);
        productService.save(product);
        redirectAttributes.addFlashAttribute("mensajeExito", "Product save successfully");
        return "redirect:/products";

    }

    private String saveImage(MultipartFile imagen) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost=new HttpPost("https://api.imgbb.com/1/upload");
            MultipartEntityBuilder builder= MultipartEntityBuilder.create();
            builder.addTextBody("key","164a1ccdd91e86daad0aa9adb5261a61" , ContentType.TEXT_PLAIN);
            builder.addBinaryBody("image", imagen.getInputStream(),ContentType.DEFAULT_BINARY, imagen.getOriginalFilename());

            HttpEntity multipart = builder.build();
            httpPost.setEntity(multipart);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String responseString = responseEntity != null ? EntityUtils.toString(responseEntity) : "";

            if (response.getStatusLine().getStatusCode() != 200){
                throw new IOException("Image upload failed. Status: " +
                        response.getStatusLine().getStatusCode() + ". Response: " + responseString);
            }

            JSONObject jsonResponse =new JSONObject(responseString);
            if (!jsonResponse.optBoolean("success")){
                throw new IOException("Image upload failed. Response: " + responseString);
            }

            JSONObject data =jsonResponse.getJSONObject("data");
            return data.getString("url");
        }
    }

}
