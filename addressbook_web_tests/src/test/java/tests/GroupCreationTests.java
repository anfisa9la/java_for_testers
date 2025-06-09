package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupCreationTests extends TestBase {


    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
/*
        for (var name : List.of("", "group name")) {
            for (var header : List.of("", "header")) {
                for (var footer : List.of("", "footer")) {
                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i * 10))
                    .withHeader(CommonFunctions.randomString(i * 10))
                    .withFooter(CommonFunctions.randomString(i * 10)));
        }
*/

        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>() {
        });
        result.addAll(value);
        return result;
    }


    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "name'", "", "")
        ));

        return result;
    }

    /*
    public static List<GroupData> singleRandomGroup() {
        return List.of(new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString(10)));
    }
    */

    public static Stream<GroupData> randomGroups() {
        Supplier<GroupData> randomGroup = () -> new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString(10));
        return Stream.generate(randomGroup).limit(1);
    }

/*
    @ParameterizedTest
    @MethodSource("groupProvider")
    public void testCreateMultipleGroups(GroupData group) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

 */

    @ParameterizedTest
    @MethodSource("randomGroups")
    public void testCreateGroup(GroupData group) {
        var oldGroups = app.jdbc().getGroupList();

        app.groups().createGroup(group);

        var newGroups = app.jdbc().getGroupList();
        var extraGroups = newGroups.stream().filter(g -> ! oldGroups.contains(g)).toList();
        var newId = extraGroups.get(0).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newId));

        Assertions.assertEquals(Set.of(newGroups), Set.copyOf(expectedList));

    }

    @ParameterizedTest
    @MethodSource("randomGroups")
    public void testCreateGroupHbm(GroupData group) {
        var oldGroups = app.hbm().getGroupList();

        app.groups().createGroup(group);

        var newGroups = app.hbm().getGroupList();
        var maxId = newGroups.get(newGroups.size() - 1).id();
        var expectedList = new ArrayList<>(oldGroups);

        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);

        expectedList.add(group.withId(maxId));
        expectedList.sort((compareById));
        Assertions.assertEquals(newGroups, expectedList);

    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void testCreateGroupNegative(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();

        Assertions.assertEquals(oldGroups, newGroups);
    }
}
