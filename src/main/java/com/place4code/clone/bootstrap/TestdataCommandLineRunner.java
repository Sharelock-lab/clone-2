package com.place4code.clone.bootstrap;

import com.place4code.clone.model.Bookmark;
import com.place4code.clone.model.Comment;
import com.place4code.clone.model.Heart;
import com.place4code.clone.model.Post;
import com.place4code.clone.model.Role;
import com.place4code.clone.model.User;
import com.place4code.clone.repository.BookmarkRepository;
import com.place4code.clone.repository.CommentRepository;
import com.place4code.clone.repository.HeartRepository;
import com.place4code.clone.repository.PostRepository;
import com.place4code.clone.repository.RoleRepository;
import com.place4code.clone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@Component
public class TestdataCommandLineRunner implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final BookmarkRepository bookmarkRepository;
    private final CommentRepository commentRepository;
    private final HeartRepository heartRepository;

    @Override
    public void run(String... args) throws Exception {

        final Role user = roleRepository.save(Role.builder().name("ROLE_USER").build());
        final Role admin = roleRepository.save(Role.builder().name("ROLE_ADMIN").build());

        final User sheldon = userRepository.save(User.builder().enabled(true).password("$2a$12$BP87lG0WM03lEtt5SOPZn./qUcLuFr1iuDxPQD9T0pWZcfmz5UURq").roles(Collections.singleton(admin)).email("Sheldon@LeeCooper.com").name("Sheldon").country("Polska").description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").build());
        final User leonard = userRepository.save(User.builder().enabled(true).password("$2a$12$BP87lG0WM03lEtt5SOPZn./qUcLuFr1iuDxPQD9T0pWZcfmz5UURq").roles(Set.of(admin, user)).email("Leonard@Hofstadter.com").name("Leonardopolis").country("Leonardopolis").description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").build());
        final User penny = userRepository.save(User.builder().enabled(true).password("$2a$12$BP87lG0WM03lEtt5SOPZn./qUcLuFr1iuDxPQD9T0pWZcfmz5UURq").roles(Collections.singleton(admin)).email("Penny@Hofstadter.com").name("Penny4B").country("Wielka Brytania").description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").build());
        final User howard = userRepository.save(User.builder().enabled(true).password("$2a$12$BP87lG0WM03lEtt5SOPZn./qUcLuFr1iuDxPQD9T0pWZcfmz5UURq").roles(Set.of(user)).email("HowardJoel@Wolowitz.com").name("Wolo_Wizard").country("USA").description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").build());
        final User rajesh = userRepository.save(User.builder().enabled(true).password("$2a$12$BP87lG0WM03lEtt5SOPZn./qUcLuFr1iuDxPQD9T0pWZcfmz5UURq").roles(Collections.singleton(admin)).email("RajeshRamayan@Koothrappali.com").name("Raj").country("Hiszpania").description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").build());
        final User bernadette = userRepository.save(User.builder().enabled(true).password("$2a$12$BP87lG0WM03lEtt5SOPZn./qUcLuFr1iuDxPQD9T0pWZcfmz5UURq").roles(Set.of(admin, user)).email("BernadetteMarianne@Rostenkowski.pl").name("B4rnad4tt4").country("Wybrzeże Kości Słoniowej").description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").build());
        final User amy = userRepository.save(User.builder().enabled(true).password("$2a$12$BP87lG0WM03lEtt5SOPZn./qUcLuFr1iuDxPQD9T0pWZcfmz5UURq").roles(Collections.singleton(admin)).email("AmyFarrah@Fowler.com").name("Shemy").country("Francja").description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").build());
        final User stuart = userRepository.save(User.builder().enabled(true).password("$2a$12$BP87lG0WM03lEtt5SOPZn./qUcLuFr1iuDxPQD9T0pWZcfmz5UURq").roles(Collections.singleton(admin)).email("Stuart@Bloom.com").name("Comicsguy").country("Chiny").description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.").build());

        final Post sheldon1Post = postRepository.save(Post.builder().user(sheldon).text("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s").views(10).build());
        final Post sheldon2Post = postRepository.save(Post.builder().user(sheldon).text("It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s").views(23).build());
        final Post sheldon3Post = postRepository.save(Post.builder().user(sheldon).text("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old").views(1).build());
        final Post leonard1Post = postRepository.save(Post.builder().user(leonard).text("Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage").views(32).build());
        final Post leonard2Post = postRepository.save(Post.builder().user(leonard).text("Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC.").views(53).build());
        final Post pennyPost = postRepository.save(Post.builder().user(penny).text(" This book is a treatise on the theory of ethics, very popular during the Renaissance.").views(63).build());
        final Post howardPost = postRepository.save(Post.builder().user(howard).text("The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.").views(121).build());
        final Post rajeshPost = postRepository.save(Post.builder().user(rajesh).text("The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form").views(21).build());
        final Post bernadettePost = postRepository.save(Post.builder().user(bernadette).text("It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.").views(12).build());
        final Post amyPost = postRepository.save(Post.builder().user(amy).text("The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.").views(40).build());

        bookmarkRepository.save(Bookmark.builder().user(sheldon).post(sheldon1Post).build());
        bookmarkRepository.save(Bookmark.builder().user(sheldon).post(sheldon2Post).build());
        bookmarkRepository.save(Bookmark.builder().user(sheldon).post(sheldon3Post).build());
        bookmarkRepository.save(Bookmark.builder().user(penny).post(leonard1Post).build());
        bookmarkRepository.save(Bookmark.builder().user(rajesh).post(howardPost).build());

        commentRepository.save(Comment.builder().user(sheldon).post(sheldon1Post).text("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.").build());
        commentRepository.save(Comment.builder().user(sheldon).post(sheldon1Post).text("If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text.").build());
        commentRepository.save(Comment.builder().user(sheldon).post(sheldon2Post).text("Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).").build());
        commentRepository.save(Comment.builder().user(sheldon).post(leonard1Post).text("It uses a dictionary of over 200 Latin words").build());
        commentRepository.save(Comment.builder().user(sheldon).post(leonard1Post).text("All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet.").build());
        commentRepository.save(Comment.builder().user(sheldon).post(pennyPost).text("Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\"").build());
        commentRepository.save(Comment.builder().user(sheldon).post(pennyPost).text("The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy.").build());

        commentRepository.save(Comment.builder().user(leonard).post(sheldon1Post).text("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable.").build());
        commentRepository.save(Comment.builder().user(leonard).post(pennyPost).text("It uses a dictionary of over 200 Latin words").build());
        commentRepository.save(Comment.builder().user(leonard).post(pennyPost).text("Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\"").build());
        commentRepository.save(Comment.builder().user(leonard).post(pennyPost).text("All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet.").build());
        commentRepository.save(Comment.builder().user(leonard).post(rajeshPost).text("If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text.").build());

        commentRepository.save(Comment.builder().user(penny).post(sheldon3Post).text("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour").build());
        commentRepository.save(Comment.builder().user(penny).post(leonard2Post).text("It uses a dictionary of over 200 Latin words").build());
        commentRepository.save(Comment.builder().user(penny).post(leonard2Post).text("Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\"").build());
        commentRepository.save(Comment.builder().user(penny).post(leonard1Post).text("If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text.").build());
        commentRepository.save(Comment.builder().user(penny).post(amyPost).text("Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).").build());
        commentRepository.save(Comment.builder().user(penny).post(amyPost).text("All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet.").build());

        commentRepository.save(Comment.builder().user(howard).post(rajeshPost).text("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour").build());
        commentRepository.save(Comment.builder().user(howard).post(rajeshPost).text("It uses a dictionary of over 200 Latin words").build());
        commentRepository.save(Comment.builder().user(howard).post(bernadettePost).text("All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet.").build());
        commentRepository.save(Comment.builder().user(howard).post(bernadettePost).text("Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\"").build());
        commentRepository.save(Comment.builder().user(howard).post(bernadettePost).text("If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text.").build());
        commentRepository.save(Comment.builder().user(howard).post(sheldon2Post).text("Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).").build());

        commentRepository.save(Comment.builder().user(rajesh).post(howardPost).text("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form").build());
        commentRepository.save(Comment.builder().user(rajesh).post(howardPost).text("If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text.").build());

        commentRepository.save(Comment.builder().user(bernadette).post(howardPost).text("Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\"").build());
        commentRepository.save(Comment.builder().user(bernadette).post(howardPost).text("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form.").build());
        commentRepository.save(Comment.builder().user(bernadette).post(howardPost).text("If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text.").build());
        commentRepository.save(Comment.builder().user(bernadette).post(amyPost).text("It uses a dictionary of over 200 Latin words").build());

        commentRepository.save(Comment.builder().user(amy).post(sheldon1Post).text("The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed tod a search l uncover many web sites still in their infancy.").build());
        commentRepository.save(Comment.builder().user(amy).post(sheldon1Post).text("If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text.").build());
        commentRepository.save(Comment.builder().user(amy).post(sheldon2Post).text("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable.").build());
        commentRepository.save(Comment.builder().user(amy).post(sheldon3Post).text("It uses a dictionary of over 200 Latin words").build());
        commentRepository.save(Comment.builder().user(amy).post(bernadettePost).text("Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).").build());
        commentRepository.save(Comment.builder().user(amy).post(pennyPost).text("Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\"").build());
        commentRepository.save(Comment.builder().user(amy).post(pennyPost).text("All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet.").build());

        commentRepository.save(Comment.builder().user(stuart).post(sheldon1Post).text("Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).").build());
        commentRepository.save(Comment.builder().user(stuart).post(sheldon2Post).text("It uses a dictionary of over 200 Latin words").build());
        commentRepository.save(Comment.builder().user(stuart).post(sheldon3Post).text("If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text.").build());
        commentRepository.save(Comment.builder().user(stuart).post(howardPost).text("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humolievable.").build());
        commentRepository.save(Comment.builder().user(stuart).post(rajeshPost).text("Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\"").build());
        commentRepository.save(Comment.builder().user(stuart).post(bernadettePost).text("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Lur, from  the undoualine in section 1.10.32.").build());
        commentRepository.save(Comment.builder().user(stuart).post(amyPost).text("The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Cs and wsum' will uncover many web sites still in their infancy.").build());
        commentRepository.save(Comment.builder().user(stuart).post(pennyPost).text("There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injecook even slightly believable.").build());
        commentRepository.save(Comment.builder().user(stuart).post(leonard1Post).text("All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet.").build());
        commentRepository.save(Comment.builder().user(stuart).post(leonard2Post).text("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of cl Ipsum comes from secty pos from a line in section 1.10.32.").build());

        heartRepository.save(Heart.builder().user(sheldon).post(sheldon1Post).build());
        heartRepository.save(Heart.builder().user(sheldon).post(sheldon2Post).build());
        heartRepository.save(Heart.builder().user(sheldon).post(sheldon3Post).build());
        heartRepository.save(Heart.builder().user(leonard).post(pennyPost).build());
        heartRepository.save(Heart.builder().user(leonard).post(rajeshPost).build());
        heartRepository.save(Heart.builder().user(howard).post(rajeshPost).build());
        heartRepository.save(Heart.builder().user(howard).post(bernadettePost).build());
        heartRepository.save(Heart.builder().user(amy).post(sheldon3Post).build());
        heartRepository.save(Heart.builder().user(amy).post(pennyPost).build());
        heartRepository.save(Heart.builder().user(stuart).post(pennyPost).build());
        heartRepository.save(Heart.builder().user(stuart).post(sheldon1Post).build());
        heartRepository.save(Heart.builder().user(stuart).post(sheldon3Post).build());
        heartRepository.save(Heart.builder().user(stuart).post(sheldon2Post).build());
        heartRepository.save(Heart.builder().user(stuart).post(leonard1Post).build());
        heartRepository.save(Heart.builder().user(stuart).post(amyPost).build());
        heartRepository.save(Heart.builder().user(stuart).post(leonard2Post).build());
        heartRepository.save(Heart.builder().user(stuart).post(rajeshPost).build());
        heartRepository.save(Heart.builder().user(stuart).post(howardPost).build());
        heartRepository.save(Heart.builder().user(stuart).post(bernadettePost).build());

    }

}
