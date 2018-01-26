package com.andforce.block.http;


import com.andforce.block.BlockChain;
import com.andforce.block.bean.Block;
import com.andforce.block.utils.JsonUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
public class BlockChainApi {

    private static BlockChain sBlockchain = BlockChain.getInstance();

    @GET
    @Path("/getChain")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMessage() {
        return JsonUtils.toStringSortByKey(sBlockchain.getChain());
    }

    @GET
    @Path("/getChainJson")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Block> getMessageJson() {
        return sBlockchain.getChain();
    }

}
