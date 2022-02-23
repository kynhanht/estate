package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.exception.InternalException;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    // Solution 1: search with JPQL and Reflection
    @Override
    public List<BuildingEntity> searchBuildings(BuildingSearchBuilder buildingSearchBuilder) {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT b \n");
        sql.append("FROM BuildingEntity AS b \n");
        sql.append("WHERE 1=1 \n");

        searchBuildingPart1(buildingSearchBuilder, sql);
        searchBuildingPart2(buildingSearchBuilder, sql);

        sql.append("GROUP BY b.id \n");

        TypedQuery<BuildingEntity> typedQuery = entityManager.createQuery(sql.toString(), BuildingEntity.class);

        List<BuildingEntity> buildingEntities = typedQuery.getResultList();


        return buildingEntities;
    }


    // Reflection
    public void searchBuildingPart1(BuildingSearchBuilder buildingSearchBuilder,  StringBuilder sql){

        try {
            Field [] fields = buildingSearchBuilder.getClass().getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                String name = field.getName();
                Object objectValue = field.get(buildingSearchBuilder);
                if(!name.equals("buildingTypes") && !name.equals("staffId")
                        && !name.startsWith("rentArea") && !name.startsWith("rentPrice")){
                    if(field.getType().equals(String.class)){
                        String value = (String) objectValue;
                        if(StringUtils.isNotBlank(value)){
                            sql.append("AND b."+ name +" LIKE '%"+ objectValue +"%' \n");
                        }
                    }
                    else if(field.getType().equals(Integer.class) || field.getType().equals(Double.class)){
                        if(objectValue != null){
                            sql.append("AND b."+ name +" = "+ objectValue +" \n");
                        }
                    }
                }
            }

        }catch (Exception ex){
            throw new InternalException(ex.getMessage());
        }

    }

    // Other case
    private void searchBuildingPart2(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {

        // staffId
        if(buildingSearchBuilder.getStaffId()!=null){
            sql.append("AND EXISTS (SELECT u FROM b.users AS u WHERE u.id = "
                    + buildingSearchBuilder.getStaffId()+ " ) \n");
        }
        // rentAreaFrom vs rentAreaTo
        if(buildingSearchBuilder.getRentAreaFrom()!=null || buildingSearchBuilder.getRentAreaTo()!=null){
            sql.append("AND EXISTS (SELECT ra FROM RentAreaEntity AS ra WHERE ra.building.id = b.id ");
            sql.append(fromAndToQuery(" ra.value", buildingSearchBuilder.getRentAreaFrom(), buildingSearchBuilder.getRentAreaTo()));
            sql.append(")\n");
        }

        // rentPriceFrom vs rentPriceTo
        if(buildingSearchBuilder.getRentPriceFrom() != null || buildingSearchBuilder.getRentPriceTo() != null){
           sql.append(fromAndToQuery("b.rentPrice", buildingSearchBuilder.getRentPriceFrom(), buildingSearchBuilder.getRentPriceTo()));
           sql.append("\n");
        }


        // buildingTypes
        if(buildingSearchBuilder.getBuildingTypes() !=null && !buildingSearchBuilder.getBuildingTypes().isEmpty()){
            sql.append("AND ( ");
            String buildingTypes = buildingSearchBuilder.getBuildingTypes()
                    .stream()
                    .map(buildingType -> "b.buildingTypes like '%"+ buildingType + "%'")
                    .collect(Collectors.joining(" OR "));
            sql.append(buildingTypes);
            sql.append(" )");
            sql.append("\n");
        }

    }

    private String fromAndToQuery(String column, Object from, Object to){
        String fromQuery = "";
        String toQuery = "";
        if(from != null){
            fromQuery = String.format(" AND %s >= %s", column, from);
        }
        if(to != null){
            toQuery = String.format(" AND %s <= %s", column, to);
        }
        return fromQuery.concat(toQuery);
    }

    // Solution 2: search with native query
    public List<BuildingEntity> searchBuildingsNative(BuildingSearchBuilder buildingSearchBuilder) {

        Map<String, Object> params = new HashMap<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT b.* \n");
        sql.append("FROM buildings AS b \n");

        sql.append("WHERE 1=1 \n");
        // name
        if (StringUtils.isNotBlank(buildingSearchBuilder.getName())) {
            sql.append("AND b.name like CONCAT('%', :name ,'%') \n");
            params.put("name",buildingSearchBuilder.getName());
        }
        // floorArea
        if (buildingSearchBuilder.getFloorArea() != null) {
            sql.append("AND b.floor_area = :floorArea \n");
            params.put("floorArea", buildingSearchBuilder.getFloorArea());

        }
        // districtCode
        if (StringUtils.isNotBlank(buildingSearchBuilder.getDistrictCode())) {
            sql.append("AND b.district_code like CONCAT('%', :districtCode ,'%') \n");
            params.put("districtCode", buildingSearchBuilder.getDistrictCode());
        }
        // ward
        if (StringUtils.isNotBlank(buildingSearchBuilder.getWard())) {
            sql.append("AND b.ward like CONCAT('%', :ward ,'%') \n");
            params.put("ward", buildingSearchBuilder.getWard());
        }
        // street
        if (StringUtils.isNotBlank(buildingSearchBuilder.getStreet())) {
            sql.append("AND b.street like CONCAT('%', :street ,'%') \n");
            params.put("street", buildingSearchBuilder.getStreet());
        }
        // numberOfBasement
        if (buildingSearchBuilder.getNumberOfBasement() != null) {
            sql.append("AND b.number_of_basement = :numberOfBasement \n");
            params.put("numberOfBasement", buildingSearchBuilder.getNumberOfBasement());
        }
        // direction
        if (StringUtils.isNotBlank(buildingSearchBuilder.getDirection())) {
            sql.append("AND b.direction like CONCAT('%', :direction ,'%') \n");
            params.put("direction", buildingSearchBuilder.getDirection());
        }
        // level
        if (StringUtils.isNotBlank(buildingSearchBuilder.getLevel())) {
            sql.append("AND b.level like CONCAT('%', :level ,'%') \n");
            params.put("level", buildingSearchBuilder.getLevel());
        }


        // rentAreaFrom vs rentAreaTo
        if(buildingSearchBuilder.getRentAreaFrom()!=null || buildingSearchBuilder.getRentAreaTo()!=null){

            sql.append("AND EXISTS (SELECT * FROM rent_area AS ra WHERE ra.building_id = b.id ");
            if(buildingSearchBuilder.getRentAreaFrom() != null){
                sql.append("AND ra.value >= :rentAreaFrom ");
                params.put("rentAreaFrom", buildingSearchBuilder.getRentAreaFrom());
            }
            if(buildingSearchBuilder.getRentAreaTo() !=null){
                sql.append("AND ra.value <= :rentAreaTo ");
                params.put("rentAreaTo", buildingSearchBuilder.getRentAreaTo());
            }
            sql.append(") \n");
        }

        // rentPriceFrom vs rentPriceTo
        if(buildingSearchBuilder.getRentPriceFrom() != null){
            sql.append("AND b.rent_price>= :rentPriceFrom ");
            params.put("rentPriceFrom", buildingSearchBuilder.getRentPriceFrom());
        }
        if(buildingSearchBuilder.getRentPriceTo() != null){
            sql.append("AND b.rent_price<= :rentPriceTo ");
            params.put("rentPriceTo", buildingSearchBuilder.getRentPriceTo());
        }

        // managerName
        if(StringUtils.isNotBlank(buildingSearchBuilder.getManagerName())){
            sql.append("AND b.manager_name like CONCAT('%', :managerName ,'%') \n");
            params.put("managerName", buildingSearchBuilder.getManagerName());
        }

        // managerPhone
        if(StringUtils.isNotBlank(buildingSearchBuilder.getManagerPhone())){
            sql.append("AND b.manager_phone like CONCAT('%', :managerPhone ,'%') \n");
            params.put("managerPhone", buildingSearchBuilder.getManagerPhone());
        }

        // staffId
        if(buildingSearchBuilder.getStaffId()!=null){
            sql.append("AND EXISTS (SELECT * FROM assignment_building AS ab WHERE ab.building_id = b.id AND ab.user_id = :staffId) \n");
            params.put("staffId", buildingSearchBuilder.getStaffId());
        }

        // buildingTypes
        if(buildingSearchBuilder.getBuildingTypes() !=null && !buildingSearchBuilder.getBuildingTypes().isEmpty()){

            String buildingTypes  =buildingSearchBuilder.getBuildingTypes()
                    .stream()
                    .collect(Collectors.joining("|"));
            sql.append("AND b.building_types REGEXP :buildingTypes \n");
            params.put("buildingTypes", buildingTypes);


        }

        sql.append("GROUP BY b.id \n");



        Query query = entityManager.createNativeQuery(sql.toString(),BuildingEntity.class);

        for(String name : params.keySet() ){
            query.setParameter(name, params.get(name));
        }
        List<BuildingEntity> buildingEntities = query.getResultList();

        return buildingEntities;
    }


}
