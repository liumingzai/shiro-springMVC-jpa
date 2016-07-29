<#macro floor8Template floor8List floorList>
    <#assign floor8ListChildren = floor8List.children>
    <#assign floor8ListChildrenPic = floor8ListChildren.floor8Pic>
    <#assign floor8ListChildrenPic01 = floor8ListChildrenPic.imgList[0]>
    <#assign floor8ListChildrenPic02 = floor8ListChildrenPic.imgList[1]>
    <#assign floor8ListChildrenPic03 = floor8ListChildrenPic.imgList[2]>
    <#assign floor8ListChildrenPic04 = floor8ListChildrenPic.imgList[3]>
    <#assign floor8ListChildrenPic05 = floor8ListChildrenPic.imgList[4]>
    <#assign floor8ListChildrenPic06 = floor8ListChildrenPic.imgList[5]>
    <#assign floor8ListChildrenPic07 = floor8ListChildrenPic.imgList[6]>
    <#assign floor8ListChildrenPic08 = floor8ListChildrenPic.imgList[7]>
    <#assign floor8Pic1text = floor8ListChildren.floor8Pic1text>
    <#assign floor8Pic4text = floor8ListChildren.floor8Pic4text>
    <div class=" w floor" data-title="${(floor8List.modelName)!}" data-code-index="${(floorList?size+1)!}">
        <div class="template09 m">
            <div class="mt">
                <div class="mtTop"><span class="h2_text">${(floorList?size+1)!}F</span><h2 class="edit-mode"  modelId="${(floor8List.modelId)!}">${(floor8List.modelName)!}</h2></div>
            </div>
            <div class="mc">
                <div class="side">
                    <div class="side-inner">
                        <div class="banner edit-mode" modelId="${(floor8ListChildrenPic.modelId)!}" modelType="${(floor8ListChildrenPic.modelType)!}">
                            <a href="${(floor8ListChildrenPic01.href)!'javascript:void(0)'}" target="_blank" data-code="${floor8ListChildrenPic01.modelId!}-1">
                                <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" width=285 height=480 data-lazy-init="${(floor8ListChildrenPic01.src)!}" data-lazy-img="0" width="285" height="480" alt="${(floor8ListChildrenPic01.alt)!}" title="">
                            </a>
                        </div>
                        <ul class="<#if floor8Pic1text.children.floor8Pic1textUp.linkSize gt 2 >themes<#else>themes02</#if> theme-opacity01 edit-mode" modelId="${(floor8Pic1text.children.floor8Pic1textUp.modelId)!}" modelType="${(floor8Pic1text.children.floor8Pic1textUp.modelType)!}">
                            <#if (floor8Pic1text.children.floor8Pic1textUp.linkList)?? && (floor8Pic1text.children.floor8Pic1textUp.linkList?size>0)>
                                <#list floor8Pic1text.children.floor8Pic1textUp.linkList as floor8Pic1textUp>
                                    <li><a href="${(floor8Pic1textUp.href)!'javascript:void(0)'}" target="_blank" data-code="${(floor8Pic1text.children.floor8Pic1textUp.modelId)!}-${(floor8Pic1textUp_index +1)}">${(floor8Pic1textUp.text)!}</a></li>
                                </#list>
                            </#if>
                        </ul>
                        <div class="<#if floor8Pic1text.children.floor8Pic1textUp.linkSize gt 2 >words<#else>words02</#if> words-opacity01 edit-mode" modelId="${(floor8Pic1text.children.floor8Pic1textDown.modelId)!}" modelType="${(floor8Pic1text.children.floor8Pic1textDown.modelType)!}">
                            <div class="words-text-block">
                                <#if (floor8Pic1text.children.floor8Pic1textDown.linkList)?? && (floor8Pic1text.children.floor8Pic1textDown.linkList?size>0)>
                                    <#list floor8Pic1text.children.floor8Pic1textDown.linkList as floor8Pic1textDown>
                                        <a href="${(floor8Pic1textDown.href)!'javascript:void(0)'}" target="_blank" data-code="${(floor8Pic1text.children.floor8Pic1textDown.modelId)!}-${(floor8Pic1textDown_index + 1)}">${(floor8Pic1textDown.text)!}</a>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="main">
                    <div class="main-inner">
                        <ul class="main-body">
                            <li class="block2">
                                <a href="${(floor8ListChildrenPic02.href)!'javascript:void(0)'}" target="_blank" data-code="${(floor8ListChildrenPic02.modelId)!}-2">
                                    <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" data-lazy-init="${(floor8ListChildrenPic02.src)!}" data-lazy-img="0" width="210" height="260" alt="${(floor8ListChildrenPic02.alt)!}" title="">
                                </a>
                            </li>
                            <li class="block3">
                                <a href="${(floor8ListChildrenPic03.href)!'javascript:void(0)'}" target="_blank" data-code="${(floor8ListChildrenPic03.modelId)!}-3">
                                    <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" data-lazy-init="${(floor8ListChildrenPic03.src)!}" data-lazy-img="0" width="210" height="220" alt="${(floor8ListChildrenPic03.alt)!}" title="">
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="side">
                    <div class="side-inner">
                        <div class="banner">
                            <a href="${(floor8ListChildrenPic04.href)!'javascript:void(0)'}" target="_blank" data-code=${(floor8ListChildrenPic04.modelId)!}-4">
                                <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" data-lazy-init="${(floor8ListChildrenPic04.src)!}" data-lazy-img="0" width="285" height="480" alt="${(floor8ListChildrenPic04.alt)!}" title="">
                            </a>
                        </div>
                        <ul class="<#if floor8Pic4text.children.floor8Pic4textUp.linkSize gt 2 >themes<#else>themes02</#if> theme-opacity02 edit-mode" modelId="${(floor8Pic4text.children.floor8Pic4textUp.modelId)!}" modelType="${(floor8Pic4text.children.floor8Pic4textUp.modelType)!}">
                            <#if (floor8Pic4text.children.floor8Pic4textUp.linkList)?? && (floor8Pic4text.children.floor8Pic4textUp.linkList?size>0)>
                                <#list floor8Pic4text.children.floor8Pic4textUp.linkList as floor8Pic4textUp>
                                    <li><a href="${(floor8Pic4textUp.href)!'javascript:void(0)'}" target="_blank" data-code="${(floor8Pic4text.children.floor8Pic4textUp.modelId)!}-${(floor8Pic4textUp_index)+1}">${(floor8Pic4textUp.text)!}</a></li>
                                </#list>
                            </#if>
                        </ul>
                        <div class="<#if floor8Pic4text.children.floor8Pic4textUp.linkSize gt 2 >words<#else>words02</#if> words-opacity02 edit-mode" modelId="${(floor8Pic4text.children.floor8Pic4textDown.modelId)!}" modelType="${(floor8Pic4text.children.floor8Pic4textDown.modelType)!}">
                            <div class="words-text-block">
                                <#if (floor8Pic4text.children.floor8Pic4textDown.linkList)?? && (floor8Pic4text.children.floor8Pic4textDown.linkList?size>0)>
                                    <#list floor8Pic4text.children.floor8Pic4textDown.linkList as floor8Pic4textDown>
                                        <a href="${(floor8Pic4textDown.href)!'javascript:void(0)'}" target="_blank" data-code="${(floor8Pic4text.children.floor8Pic4textDown.modelId)!}-${floor8Pic4textDown_index+1}">${(floor8Pic4textDown.text)!}</a>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="main">
                    <div class="main-inner">
                        <ul class="main-body">
                            <li class="block2">
                                <a href="${(floor8ListChildrenPic05.href)!'javascript:void(0)'}" target="_blank"  data-code="${(floor8ListChildrenPic05.modelId)!}-5">
                                    <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" data-lazy-init="${(floor8ListChildrenPic05.src)!}" data-lazy-img="0" width="210" height="260" alt="${(floor8ListChildrenPic05.alt)!}" title="">
                                </a>
                            </li>
                            <li class="block3">
                                <a href="${(floor8ListChildrenPic06.href)!'javascript:void(0)'}" target="_blank" data-code="${(floor8ListChildrenPic05.modelId)!}-6">
                                    <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" data-lazy-init="${(floor8ListChildrenPic06.src)!}" data-lazy-img="0" width="210" height="220" alt="${(floor8ListChildrenPic06.alt)!}" title="">
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="main db-990">
                    <div class="main-inner">
                        <ul class="main-body">
                            <li class="block2">
                                <a href="${(floor8ListChildrenPic07.href)!'javascript:void(0)'}" target="_blank" data-code="${(floor8ListChildrenPic05.modelId)!}-7">
                                    <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" data-lazy-init="${(floor8ListChildrenPic07.src)!}" data-lazy-img="0" width="210" height="260" alt="${(floor8ListChildrenPic07.alt)!}" title="">
                                </a>
                            </li>
                            <li class="block3">
                                <a href="${(floor8ListChildrenPic08.href)!'javascript:void(0)'}" target="_blank" data-code="${(floor8ListChildrenPic05.modelId)!}-8">
                                    <img class="lazyloading" src="${storeConfiguration.stageImageServer}/images/grey.gif" data-lazy-init="${(floor8ListChildrenPic08.src)!}" data-lazy-img="0" width="210" height="220" alt="${(floor8ListChildrenPic08.alt)!}" title="">
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>
