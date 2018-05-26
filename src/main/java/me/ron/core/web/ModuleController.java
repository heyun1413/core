package me.ron.core.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.ron.core.bean.Pager;
import me.ron.core.dto.TableHeader;
import me.ron.core.entity.Module;
import me.ron.core.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * @author ron
 */
@RestController
@RequestMapping("modules")
@Api("模块api")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @ApiOperation("分页获取模块列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", dataType = "int", paramType = "query")
    })
    @GetMapping
    public ResponseEntity<Pager<Module>> modules(
            @RequestParam(required = false, defaultValue = "1") int pageNum,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(moduleService.getModules(pageNum, pageSize));
    }

    @ApiOperation("保存模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "module", dataType = "Module", paramType = "body"),
    })
    @PostMapping
    public ResponseEntity<String> save(
            @Validated @RequestBody Module module,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        moduleService.add(module);
        return ResponseEntity.created(URI.create("")).body(null);
    }

    @ApiOperation("修改模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "module", dataType = "Module", paramType = "body"),
    })
    @PutMapping
    public ResponseEntity<String> modify(
            @Validated @RequestBody Module module,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        moduleService.update(module);
        return ResponseEntity.ok().body(null);
    }

    @ApiOperation("删除模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "int", paramType = "query"),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<String> remove(@PathVariable("id") Integer id) {
        moduleService.remove(id);
        return ResponseEntity.ok().body(null);
    }

    @ApiOperation("获取模块信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", dataType = "int", paramType = "query"),
    })
    @GetMapping("{id}")
    public ResponseEntity<Module> get(@PathVariable("id") Integer id) {
        Module module = moduleService.getByCascade(id);
        if (module == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(module);
    }

    @GetMapping("headers")
    public ResponseEntity<List<TableHeader>> getTableHeaders() {
        return ResponseEntity.ok(Arrays.asList(
                new TableHeader("name", "名称"),
                new TableHeader("createTime", "创建时间"),
                new TableHeader("updateTime", "更新时间"),
                new TableHeader("status", "状态"),
                new TableHeader("operations", "操作")
        ));
    }

}
