<#macro floorTemplates floor floorList gomeImgPath gomeCssPath>
    <#assign floorListContent = floorList[floor?number-1]>
    <div class="w floor"  data-title="${(floorListContent.modelName)!}" data-code-index="${(floor)!}">
    <#--提取后台数据，截取字符串，提取出相应数字序号-->
        <#if floorListContent.flag??&& (floorListContent.flag=="A模板")>
            <#assign tempalteStyle="template01">
        <#elseif floorListContent.flag??&& (floorListContent.flag=="B模板")>
            <#assign tempalteStyle="template02">
        <#else>
            <#assign tempalteStyle="template01">
        </#if>
        <div class="${(tempalteStyle)!} m">
            <div class="mt">
               <div class="mtTop"><span class="h2_text">${(floor)!}F</span><h2 class="edit-mode"   modelId="${(floorListContent.modelId)!}">${(floorListContent.modelName)!}</h2></div>
                <ul class="tab" floor-info="${(floor?number-1)}">
                <#--首tab总节点-->
                    <#assign firstTab = floorListContent.children.firstTab>
                    <li class="tab-item tab-selected edit-mode" modelId="${(firstTab.modelId)!}"><a href="javascript:void(0)">${(firstTab.modelName)!}</a></li>
                <#--内签tab总节点-->
                    <#assign tabList = floorListContent.children.tab.templates>
                    <#if (tabList)?? && (tabList?size>0)>
                        <#list tabList as tab>
                            <li class="tab-item" tab-data-load="0" key="${(tab.keyword)?substring(17)}" modelId="${tab.modelId!}"><a href="javascript:void(0)">${(tab.modelName)!}</a></li>
                        </#list>
                    </#if>
                </ul>
            </div>
            <div class="mc">
                <div class="tab-page">
                    <a href="javascript:void(0);" class="tab-next" style="display:none"><span class="next-arrow-span"></span></a>
                </div>
                <div class="side">
                    <#assign firstTabLogoPic = floorListContent.children.firstTab.children.logoPic>
                    <#assign firstTabNav = floorListContent.children.firstTab.children.nav>
                    <div class="side-inner" style="background:${(firstTabNav.children.navUp.flag)!'#eee'}">
                    <#--首tab左侧logo图片节点-->
                        <div class="banner edit-mode" modelId="${(firstTabLogoPic.modelId)!}" modelType="${(firstTabLogoPic.modelType)!}">
                            <a href="${(firstTabLogoPic.imgList[0].href)!}" target="_blank" data-code="${(firstTabLogoPic.imgList[0].modelId)!}">
                                <img class="lazyloading" width="209" height="260" src="${storeConfiguration.stageImageServer}/images/grey.gif" data-lazy-init="${(firstTabLogoPic.imgList[0].src)!}" alt="${(firstTabLogoPic.imgList[0].alt)!}" title="" data-lazy-img="0">
                            </a>
                        </div>
                    <#--首tab左侧上下导航-->
                        <ul class="channel edit-mode" modelId="${(firstTabNav.children.navUp.modelId)!}" modelType="${(firstTabNav.children.navUp.modelType)!}" style="background:${(firstTabNav.children.navUp.flag)!'#eee'}">
                            <#if (firstTabNav.children.navUp.linkList)?? && (firstTabNav.children.navUp.linkList?size>0)>
                                <#list firstTabNav.children.navUp.linkList as upNav>
                                    <li><a href="${(upNav.href)!}" target="_blank" data-code="${(firstTabNav.children.navUp.modelId)!}-${upNav_index+1}">${(upNav.text)!}</a></li>
                                </#list>
                            </#if>
                        </ul>
                        <ul class="words edit-mode" modelId="${(firstTabNav.children.navDown.modelId)!}" modelType="${(firstTabNav.children.navDown.modelType)!}" style="background:${(firstTabNav.children.navDown.flag)!'#eee'}">
                            <#if (firstTabNav.children.navDown.linkList)?? && (firstTabNav.children.navDown.linkList?size>0)>
                                <#list firstTabNav.children.navDown.linkList as navDown>
                                    <li><a <#if (navDown.textOtherAttr.extend)?? && (navDown.textOtherAttr.extend == "1")>class="red"</#if> href="${(navDown.href)!}" target="_blank" data-code="${(firstTabNav.children.navDown.modelId)!}-${navDown_index+1}">${(navDown.text)!}</a></li>
                                </#list>
                            </#if>
                        </ul>
                    </div>
                </div>
                <div class="main" data-code-index="1">
                <#--首tab右侧产品组-->
                    <#assign productPic = floorListContent.children.firstTab.children.productPic>
                    <div class="main-inner">
                        <ul class="main-body edit-mode" modelId="${(productPic.modelId)!}" modelType="${(productPic.modelType)!}">
                        <li class="block0">
                            <#if (productPic.imgList)?? && (productPic.imgList?size>0) && (tempalteStyle=="template01")>
                                <#list productPic.imgList as imgList>
                                    <#if (imgList_index == 0)>
                                        <li class="block2">
                                            <a href="${(imgList.href)!'javascript:void(0)'}" target="_blank" data-code="${imgList.modelId!}-1">
                                                <img class="lazyloading" main-inner src="${storeConfiguration.stageImageServer}/images/grey.gif"  alt="${(imgList.alt)!}" title=""  width="209" height="259" data-lazy-init="${(imgList.src)!}"  data-lazy-img="0"/>
                                            </a>
                                        </li>
                                    <#elseif ((imgList_index == 1))>
                                        <li class="block2 db-990">
                                            <a href="${(imgList.href)!'javascript:void(0)'}" target="_blank"  data-code="${imgList.modelId!}-2">
                                                <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" alt="${(imgList.alt)!}" title=""  width="209" height="259" data-lazy-init="${(imgList.src)!}"  data-lazy-img="0"/>
                                            </a>
                                        </li>
                                    <#elseif ((imgList_index == 2))>
                                        <li class="block1">
                                            <a href="${(imgList.href)!'javascript:void(0)'}" target="_blank" data-code="${imgList.modelId!}-3">
                                                <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" alt="${(imgList.alt)!}" title=""  width="189" height="218" data-lazy-init="${(imgList.src)!}"  data-lazy-img="0"/>
                                            </a>
                                        </li>
                                    <#elseif ((imgList_index == 3))>
                                        <li class="block1">
                                            <a href="${(imgList.href)!'javascript:void(0)'}" target="_blank" data-code="${imgList.modelId!}-4">
                                                <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" alt="${(imgList.alt)!}" title=""  width="189" height="218" data-lazy-init="${(imgList.src)!}"  data-lazy-img="0"/>
                                            </a>
                                        </li>
                                    <#elseif ((imgList_index == 4))>
                                        <li class="block1">
                                            <a href="${(imgList.href)!'javascript:void(0)'}" target="_blank" data-code="${imgList.modelId!}-5">
                                                <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" alt="${(imgList.alt)!}" title=""  width="189" height="218" data-lazy-init="${(imgList.src)!}"  data-lazy-img="0"/>
                                            </a>
                                        </li>
                                    <#elseif ((imgList_index == 5))>
                                        <li class="block3">
                                            <a href="${(imgList.href)!'javascript:void(0)'}" target="_blank" data-code="${imgList.modelId!}-6">
                                                <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" alt="${(imgList.alt)!}" title=""  width="209" height="218" data-lazy-init="${(imgList.src)!}"  data-lazy-img="0"/>
                                            </a>
                                        </li>
                                    <#elseif ((imgList_index == 6))>
                                        <li class="block3 db-990">
                                            <a href="${(imgList.href)!'javascript:void(0)'}" target="_blank" data-code="${imgList.modelId!}-7">
                                                <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" alt="${(imgList.alt)!}" title=""  width="209" height="218" data-lazy-init="${(imgList.src)!}"  data-lazy-img="0"/>
                                            </a>
                                        </li>
                                    <#else >
                                        <li class="block3 db-990">
                                            <a href="${(imgList.href)!'javascript:void(0)'}" target="_blank" data-code="${imgList.modelId!}-8">
                                                <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" alt="${(imgList.alt)!}" title=""  width="209" height="218" data-lazy-init="${(imgList.src)!}"  data-lazy-img="0"/>
                                            </a>
                                        </li>
                                    </#if>
                                </#list>
                            <#elseif (productPic.imgList)?? && (productPic.imgList?size>0) && (tempalteStyle=="template02")>
                                <#list productPic.imgList as imgList>
                                    <#if (imgList_index == 0)>
                                        <li class="block2">
                                            <a href="${(imgList.href)!}" target="_blank" data-code="${imgList.modelId!}-1">
                                                <img class="lazyloading" width="209" height="259" alt="${(imgList.alt)!}" title="" src="${storeConfiguration.stageImageServer}/images/grey.gif" data-lazy-init="${(imgList.src)!}"  data-lazy-img="0"/>
                                            </a>
                                        </li>
                                    <#elseif ((imgList_index == 1))>
                                        <li class="block2">
                                            <a href="${(imgList.href)!}" target="_blank" data-code="${imgList.modelId!}-2">
                                                <img class="lazyloading" width="209" height="259" alt="${(imgList.alt)!}" title=""  src="${storeConfiguration.stageImageServer}/images/grey.gif" data-lazy-init="${(imgList.src)!}"  data-lazy-img="0"/>
                                            </a>
                                        </li>
                                    <#elseif ((imgList_index == 2))>
                                        <li class="block2 db-990">
                                            <a href="${(imgList.href)!}" target="_blank" data-code="${imgList.modelId!}-3">
                                                <img class="lazyloading" width="209" height="259" alt="${(imgList.alt)!}" title=""  src="${storeConfiguration.stageImageServer}/images/grey.gif" data-lazy-init="${(imgList.src)!}"  data-lazy-img="0"/>
                                            </a>
                                        </li>
                                    <#elseif ((imgList_index == 3))>
                                        <li class="block3">
                                            <a href="${(imgList.href)!}" target="_blank" data-code="${imgList.modelId!}-4">
                                                <img class="lazyloading" width="209" height="218" alt="${(imgList.alt)!}" title=""  src="${storeConfiguration.stageImageServer}/images/grey.gif" data-lazy-init="${(imgList.src)!}"  data-lazy-img="0"/>
                                            </a>
                                        </li>
                                    <#elseif ((imgList_index == 4))>
                                        <li class="block3">
                                            <a href="${(imgList.href)!}" target="_blank" data-code="${imgList.modelId!}-5">
                                                <img class="lazyloading" width="209" height="218" alt="${(imgList.alt)!}" title=""  src="${storeConfiguration.stageImageServer}/images/grey.gif" data-lazy-init="${(imgList.src)!}"  data-lazy-img="0"/>
                                            </a>
                                        </li>
                                    <#elseif ((imgList_index == 5))>
                                        <li class="block3 db-990">
                                            <a href="${(imgList.href)!}" target="_blank" data-code="${imgList.modelId!}-6">
                                                <img class="lazyloading" width="209" height="218" alt="${(imgList.alt)!}" title=""  data-lazy-init="${(imgList.src)!}"  data-lazy-img="0"/>
                                            </a>
                                        </li>
                                    </#if>
                                </#list>
                            </#if>
                        </ul>
                    </div>
                    <div class="slider">
                        <div class="slider-body" style="position: relative;">
                        <#--首tab右侧幻灯-->
                            <#assign FocusPic = floorListContent.children.firstTab.children.FocusPic>
                            <ul class="slider-main edit-mode" modelId="${(FocusPic.modelId)!}" modelType="${(FocusPic.modelType)!}">
                                <#if (FocusPic.imgList)?? && (FocusPic.imgList?size>0)>
                                    <#list FocusPic.imgList as imgList>
                                        <li class="slider-panel">
                                            <a href="${(imgList.href)!'javascript:void(0);'}" target="_blank" data-code="${imgList.modelId!}-${imgList_index+1}">
                                                <#if tempalteStyle=="template01">
                                                    <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" data-lazy-init="${(imgList.src)!}" width="569" height="259" alt="${(imgList.alt)!}" title=""   data-lazy-img="0">
                                                <#elseif tempalteStyle=="template02">
                                                    <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" data-lazy-init="${(imgList.src)!}" width="359" height="478"  alt="${(imgList.alt)!}" title=""  data-lazy-img="0">
                                                </#if>
                                            </a>
                                        </li>
                                    </#list>
                                </#if>
                            </ul>
                        </div>
                    <#--<div class="slider-nav">-->
                    <#--<ul>-->
                    <#--<#list FocusPic.imgList as imgList>-->
                    <#--<li class="slider-item"></li>-->
                    <#--</#list>-->
                    <#--</ul>-->
                    <#--</div>-->
                        <div class="slider-page">
                            <a href="javascript:void(0)" class="slider-prev"><span class="prev-arrow-span"></span></a>
                            <a href="javascript:void(0)" class="slider-next"><span class="next-arrow-span"></span></a>
                        </div>
                    </div>
                </div>
                <#if (tabList)?? && (tabList?size>0)>
                    <#list tabList as tab>
                        <div class="main edit-mode" modelId="${(tab.modelId)!}" style="display:none;overflow:hidden" data-code-index="${(tab_index + 2)}"></div>
                    </#list>
                </#if>
            </div>
        </div>
        <#-- 品牌一条龙 *** 开始 -->
        <#if floorListContent.children.firstTab.children.brandImage??>
            <#assign brandImage = floorListContent.children.firstTab.children.brandImage>
            <div class="brands">
                <span class="brands-pre"><img data-lazy-init="${gomeImgPath}/gmpro/1.0.0/index/1.0.0/css/i/left_normal.png" data-lazy-img="0" alt=""></span>
                <div class="brands-all edit-mode" modelId="${(brandImage.modelId)!}" modelType="${(brandImage.modelType)!}">
                    <#if (brandImage.imgList)?? && (brandImage.imgList?size>0)>
                        <#list brandImage.imgList as imgList>
                            <#if imgList_index==0>
                            <ul>
                                <li><a href="${(imgList.href)!}" title="${(imgList.title)!}" target="_blank" data-code="${(imgList.modelId)!}-${(imgList_index+1)}"><img width=100 height=30 data-lazy-init="${(imgList.src)!}" alt="${(imgList.alt)!}"   data-lazy-img="0"></a></li>
                            <#elseif (imgList_index%10)==0>
                            </ul>
                            <ul>
                                <li><a href="${(imgList.href)!}" title="${(imgList.title)!}" target="_blank" data-code="${(imgList.modelId)!}-${(imgList_index+1)}"><img width=100 height=30 data-lazy-init="${(imgList.src)!}" alt="${(imgList.alt)!}"   data-lazy-img="0"></a></li>
                            <#elseif imgList_index==(brandImage.imgList?size-1)>
                                <li><a href="${(imgList.href)!}" title="${(imgList.title)!}" target="_blank" data-code="${(imgList.modelId)!}-${(imgList_index+1)}"><img width=100 height=30 data-lazy-init="${(imgList.src)!}" alt="${(imgList.alt)!}"   data-lazy-img="0"></a></li>
                            </ul>
                            <#else>
                                <li><a href="${(imgList.href)!}" title="${(imgList.title)!}" target="_blank" data-code="${(imgList.modelId)!}-${(imgList_index+1)}"><img width=100 height=30 data-lazy-init="${(imgList.src)!}" alt="${(imgList.alt)!}"   data-lazy-img="0"></a></li>
                            </#if>
                        </#list>
                    </#if>
                </div>
                <span class="brands-nex"><img data-lazy-init="${gomeImgPath}/gmpro/1.0.0/index/1.0.0/css/i/right_normal.png" data-lazy-img="0" alt=""></span>
            </div>
        </#if>
        <#-- 品牌一条龙 *** 结束 -->
        <#--首tab底部banner-->
        <#if floorListContent.children.firstTab.children.bannerImage??>
            <#assign bannerImage = floorListContent.children.firstTab.children.bannerImage>
            <#if (bannerImage.imgList)??&&bannerImage.imgList?size &gt; 0>
                <div class="bottom-banner edit-mode"  modelId="${(bannerImage.modelId)!}" modelType="${(bannerImage.modelType)!}">
                    <#if (bannerImage.imgList)??>
                        <#if (bannerImage.imgList?size) &gt; 1>
                        <ul>
                        </#if>
                        <#list bannerImage.imgList as imgList>
                            <#if (bannerImage.imgList?size) &gt; 1>
                                <li class="bottom-banner-li-${(bannerImage.imgList?size)!}">
                            </#if>
                                <#if (imgList.href)??>
                                    <a href="${(imgList.href)!'javascript:void(0)'}" target="_blank" data-code="${(imgList.modelId)!}<#if (bannerImage.imgList?size) &gt; 1>-${(imgList_index+1)!}</#if>">
                                </#if>
                                    <img src="${storeConfiguration.stageImageServer}/images/grey.gif" data-lazy-init="${(imgList.src)!}" alt="${(imgList.alt)!}"  data-lazy-img="0">
                                <#if (imgList.href)??>
                                    </a>
                                </#if>
                            <#if (bannerImage.imgList?size) &gt; 1>
                                </li>
                            </#if>
                        </#list>
                        <#if (bannerImage.imgList?size) &gt; 1>
                        </ul>
                        </#if>
                    </#if>
                </div>
            </#if>
        </#if>
        <#-- 首tab底部banner*** 结束 -->
    </div>
</#macro>
