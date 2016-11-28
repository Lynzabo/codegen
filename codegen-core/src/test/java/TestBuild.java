/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */

import com.github.lynzabo.codegen.model.ServiceImplDTO;

/**
 *
 * @author linzhanbo .
 * @since 2016年11月19日, 13:55 .
 * @version 1.0 .
 */
public class TestBuild {
    public static void main(String[] args) {
        ServiceImplDTO.ServiceImplDTOBuilder serviceImplDTOBuilder = new ServiceImplDTO.ServiceImplDTOBuilder();
        ServiceImplDTO serviceImplDTO = serviceImplDTOBuilder.setDescription("description").setLocation("location").build();
    }
}
