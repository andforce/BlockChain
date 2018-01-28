package com.andforce.block.http;


import com.andforce.block.BlockChainHelper;
import com.andforce.block.bean.Chain;
import com.andforce.block.utils.JsonUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class BlockChainApi {

    private static BlockChainHelper sBlockchain = BlockChainHelper.getInstance();

    @GET
    @Path("/getChain")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMessage() {
        return JsonUtils.toStringSortByKey(sBlockchain.getChain());
    }

    @GET
    @Path("/getChainJson")
    @Produces(MediaType.APPLICATION_JSON)
    public Chain getMessageJson() {
        return sBlockchain.getChain();
    }

}
